package familyguy.dunkru;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SandwichTest {
    private final double CHICKEN_SANDWICH = 8.99;
    private final double FISH_SANDWICH = 9.99;
    private final double BEEF_SANDWICH = 10.99;
    private final double CHEESE_ADD_ON = 1.00;
    private final double VEGGIE_ADD_ON = 0.30;


    @Test
    public void testSingularChickenSandwich() {
        int quantity = 1;
        ChickenSandwich cs = new ChickenSandwich(quantity, "Bagel");
        assertEquals(cs.price(), CHICKEN_SANDWICH);
    }

    @Test
    public void testSingularBeefSandwich() {
        int quantity = 1;
        BeefSandwich cs = new BeefSandwich(quantity, "Wheat Bread");
        assertEquals(cs.price(), BEEF_SANDWICH);
    }

    @Test
    public void testSingularFishSandwich() {
        int quantity = 1;
        FishSandwich cs = new FishSandwich(quantity, "Sour Dough");
        assertEquals(cs.price(), FISH_SANDWICH);
    }

    @Test
    public void testNoProteinSandwich(){
        Sandwich sandwich = new Sandwich();
        assertEquals(sandwich.price(), 0);
    }

    @Test
    public void testMultipleChickenSandwich() {
        int quantity = 9;
        ChickenSandwich cs = new ChickenSandwich(quantity, "Bagel");
        assertEquals(cs.price(), CHICKEN_SANDWICH*quantity);
    }

    @Test
    public void testMultipleBeefSandwich() {
        int quantity = 9;
        BeefSandwich cs = new BeefSandwich(quantity, "Wheat Bread");
        assertEquals(cs.price(), BEEF_SANDWICH*quantity);
    }

    @Test
    public void testMultipleFishSandwich() {
        int quantity = 9;
        FishSandwich cs = new FishSandwich(quantity, "Sour Dough");
        assertEquals(cs.price(), FISH_SANDWICH*quantity);
    }

    @Test
    public void testFishSandwichWithVeggieAddOns() {
        int quantity = 1;
        FishSandwich cs = new FishSandwich(quantity, "Sour Dough");
        cs.addAddOn("Lettuce");
        cs.addAddOn("Onions");
        assertEquals(cs.price(), FISH_SANDWICH+(VEGGIE_ADD_ON*2));
    }

    @Test
    public void testBeefSandwichWithVeggieAddOns() {
        int quantity = 1;
        BeefSandwich cs = new BeefSandwich(quantity, "Wheat Bread");
        cs.addAddOn("Lettuce");
        cs.addAddOn("Onions");
        assertEquals(cs.price(), BEEF_SANDWICH+(VEGGIE_ADD_ON*2));
    }

    @Test
    public void testChickenSandwichWithVeggieAddOns() {
        int quantity = 1;
        ChickenSandwich cs = new ChickenSandwich(quantity, "Bagel");
        cs.addAddOn("Lettuce");
        cs.addAddOn("Onions");
        assertEquals(cs.price(), CHICKEN_SANDWICH+(VEGGIE_ADD_ON*2));
    }

    @Test
    public void testFishSandwichWithCheese() {
        int quantity = 1;
        FishSandwich cs = new FishSandwich(quantity, "Sour Dough");
        cs.addAddOn("cheese");
        assertEquals(cs.price(), FISH_SANDWICH+CHEESE_ADD_ON);
    }

    @Test
    public void testBeefSandwichWithCheese() {
        int quantity = 1;
        BeefSandwich cs = new BeefSandwich(quantity, "Wheat Bread");
        cs.addAddOn("cheese");
        assertEquals(cs.price(), BEEF_SANDWICH+CHEESE_ADD_ON);
    }

    @Test
    public void testChickenSandwichWithCheese() {
        int quantity = 1;
        ChickenSandwich cs = new ChickenSandwich(quantity, "Bagel");
        cs.addAddOn("cheese");
        assertEquals(cs.price(), CHICKEN_SANDWICH+CHEESE_ADD_ON);
    }

    @Test
    public void testChickenSandwichWithCheeseAndVeggies(){
        int quantity = 1;
        ChickenSandwich cs = new ChickenSandwich(quantity, "Bagel");
        cs.addAddOn("cheese");
        cs.addAddOn("Lettuce");
        cs.addAddOn("onion");
        assertEquals(cs.price(), CHICKEN_SANDWICH+CHEESE_ADD_ON+(VEGGIE_ADD_ON*2));
    }

    @Test
    public void testBeefSandwichWithCheeseAndVeggies(){
        int quantity = 1;
        BeefSandwich cs = new BeefSandwich(quantity, "Wheat Bread");
        cs.addAddOn("cheese");
        cs.addAddOn("Lettuce");
        cs.addAddOn("onion");
        assertEquals(cs.price(), BEEF_SANDWICH+CHEESE_ADD_ON+(VEGGIE_ADD_ON*2));
    }

    @Test
    public void testFishSandwichWithCheeseAndVeggies(){
        int quantity = 1;
        FishSandwich cs = new FishSandwich(quantity, "Sour Dough");
        cs.addAddOn("cheese");
        cs.addAddOn("Lettuce");
        cs.addAddOn("onion");
        assertEquals(cs.price(), FISH_SANDWICH+CHEESE_ADD_ON+(VEGGIE_ADD_ON*2));
    }




}