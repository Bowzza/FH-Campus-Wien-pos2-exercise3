package at.ac.fhcampuswien.newsanalyzer.ui;


import at.ac.fhcampuswien.newsanalyzer.ctrl.Controller;
import at.ac.fhcampuswien.newsapi.NewsApiException;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;
import at.ac.fhcampuswien.newsapi.enums.Language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInterface
{
	private Controller ctrl = new Controller();

	public void getDataFromCtrl1() {
		ctrl.process("corona", Category.health, Country.at, Endpoint.TOP_HEADLINES, Language.de, "100", "1");
	}

	public void getDataFromCtrl2() {
		// TODO implement me

		ctrl.process("bitcoin", Category.business, Country.at, Endpoint.TOP_HEADLINES, Language.de, "100", "1");
	}

	public void getDataFromCtrl3() {
		// TODO implement me
		ctrl.process("apple", Category.technology, Country.at, Endpoint.TOP_HEADLINES, Language.de, "100", "1");

	}
	
	public void getDataForCustomInput() {
		// TODO implement me
		Scanner scanner = new Scanner(System.in);
		ctrl.process(scanner.next(), Category.technology, Country.at, Endpoint.TOP_HEADLINES, Language.de, "100", "1");

	}


	public void start(){
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitle("Wählen Sie aus:");
		menu.insert("a", "Choice ABC", this::getDataFromCtrl1);
		menu.insert("b", "Choice DEF", this::getDataFromCtrl2);
		menu.insert("c", "Choice 3", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Input:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) {
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
