package concombre;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.fr.Alors;
import cucumber.api.java.fr.Etantdonnéque;
import cucumber.api.java.fr.Quand;
import io.cucumber.datatable.TableEntryTransformer;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AnnotationSteps {

    int lot1;
    int lot2;
    int total;
    String item;
    int itemValue;
    final int minExpected = 5;
    List <Map<String, String>> clientList;
    List <Map<String, String>> companyList;

    @Given("I've {int} products")
    public void i_ve_number_products(int qty) {
        lot1 = qty;
    }

    @Given("I add {int} additional products")
    public void i_had_number_additional_products(int qty) {
        lot2 = qty;
    }

    @When("I count everything I have in stock")
    public void i_count_everything_i_have_in_stock() {
        total = lot1 + lot2;
    }

    @Then("I've at least {int} products in stock")
    public void i_ve_at_least_number_products_in_stock(int expected) {
        Assert.assertTrue("There is not enough stock", total >= expected);
    }

    @Given("I need to add some {string}")
    public void i_need_to_add_some_product(String newItem) {
        item = newItem;
    }

    @Etantdonnéque("I know how much I have")
    public void i_knom_how_much_i_have() {
        switch (item) {
            case "Ladder":
                itemValue = 3;
                break;
            case "Chest":
                itemValue = 2;
                break;
            case "Table":
                itemValue = -2;
                break;
            case "Chair":
                itemValue = -1;
                break;
            case "Painting":
                itemValue = 4;
                break;
            case "Monitor":
                itemValue = 1;
                break;
            case "Oven":
                itemValue = 5;
                break;
            case "Projector":
                itemValue = -3;
                break;
            case "Bag":
                itemValue = -4;
                break;
            case "Watch":
                itemValue = -5;
                break;
            default:
                itemValue = 0;
                break;
        }
    }

    @When("I add it to the stock")
    public void i_add_it_to_the_stock() {
        total = lot1 + lot2 + itemValue;
    }

    @Then("I should have more than the minimum needed")
    public void i_should_have_more_than_the_minimum_needed() {
        Assert.assertTrue("Error detected, we need at least "+ minExpected + " " + item + ", right now we have only "+ total + " products", total >= minExpected);
    }

    @Given("today we need to setup a delivery to these clients :")
    public void today_we_need_to_setup_a_delivery_to_these_clients( List <Map<String, String>> list) {
        clientList = list;
    }

    @When("only these companies are available :")
    public void only_these_companies_are_available(List <Map<String, String>> list) {
        companyList = list;
    }

    @Then("every product can be shipped")
    public void every_product_can_be_shipped() {
        for(Map<String, String> map : clientList) {
            int check = 0;
            for(Map<String, String> mapComp : companyList) {
                //ListMultimap<String, String> multimap = ArrayListMultimap.create();
                //mapComp.forEach(multimap::putAll);
                //if(mapComp.containsEntry("country", map.get("country"))){
                //    check = 1;
                //    break;
                //}
                if(mapComp.get("country").equals(map.get("country"))){
                    check = 1;
                    break;
                }
            }
            Assert.assertTrue("Delivery to " + map.get("country") + " is not available, " + map.get("fullname") + " will not have his/her package today", check==1);
        }
    }

    @Before("@SetOne and not @SetTwo")
    public void doSomethingBefore(Scenario scenario){
        System.out.println(scenario.getName() + " is starting");
    }

    @After("@SetTwo and not @SetOne")
    public void doSomethingAfter(Scenario scenario){
        System.out.println(scenario.getName() + " is finished");
    }
}
