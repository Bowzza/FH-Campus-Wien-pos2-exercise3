package at.ac.fhcampuswien.newsanalyzer.ctrl;

import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiBuilder;
import at.ac.fhcampuswien.newsapi.NewsApiException;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;
import at.ac.fhcampuswien.newsapi.enums.Language;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {

	public static final String APIKEY = "4869756d05394fc4b25b367055328d6d";  //TODO add your api key

	public void process(String q, Category category, Country country, Endpoint endpoint, Language language, String pageSize, String page) {
		System.out.println("Start process");


		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ(q)
				.setEndPoint(endpoint)// example of how to use enums
				.setSourceCountry(country)       // example of how to use enums
				.setSourceCategory(category) // example of how to use enums
				.createNewsApi();

		NewsResponse newsResponse = newsApi.getNews();
		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(a -> System.out.println(a.getAuthor()));
			System.out.println("Anzahl der Artikel: "+newsResponse.getTotalResults()+"\n");



			Map<String, Long> countByProvider =  articles.stream().collect(Collectors.groupingBy(i -> i.getSource().getName(), Collectors.counting()));
			if(newsResponse.getTotalResults() > 0) {
				System.out.println("Provider liefert die meisten Artikel: "+ countByProvider.entrySet().stream().max((a, b) -> a.getValue() > b.getValue() ? 1 : -1).get().getKey() + "\n");
			}



			try {
				Article shortestAuthorName = articles.stream().filter(a -> a.getAuthor() != null).min(Comparator.comparing(a -> a.getAuthor().length())).get();
				System.out.println("Autor mit kürzesten Namen: " + shortestAuthorName.getAuthor()+"\n");
			} catch (NoSuchElementException e){
				System.out.println(e);
			}



			System.out.println("Nach Länge vom Titel sortiert: ");
			List<Article> sortedByTitleLength = articles.stream().sorted(Comparator.comparingInt(a -> a.getTitle().length())).collect(Collectors.toList());
			Collections.reverse(sortedByTitleLength);
			sortedByTitleLength.forEach(a -> System.out.println(a.getTitle()));
			System.out.println();



			System.out.println("Nach Titel sortiert: ");
			articles.stream().sorted(Comparator.comparing(Article::getTitle))
					.collect(Collectors.toList()).forEach(article -> System.out.println(article.getTitle()));
		}








		//TODO implement Error handling

		//TODO load the news based on the parameters

		//TODO implement methods for analysis

		System.out.println("End process");
	}
	

	public Object getData() {
		
		return null;
	}
}
