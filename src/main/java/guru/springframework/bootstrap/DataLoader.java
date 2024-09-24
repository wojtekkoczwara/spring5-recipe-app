package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;

        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        //perfet guacamole recipe
        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("Perfect guacamole");

        Set<Category> categorySet = new HashSet<>();
        categorySet.add(categoryRepository.findByDescription("Mexican").get());
        perfectGuacamole.setCategories(categorySet);

        UnitOfMeasure unit = unitOfMeasureRepository.findByDescription("Unit").get();
        UnitOfMeasure some = unitOfMeasureRepository.findByDescription("Some").get();
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch").get();
        UnitOfMeasure cup = unitOfMeasureRepository.findByDescription("Cup").get();
        UnitOfMeasure pint = unitOfMeasureRepository.findByDescription("Pint").get();

        Ingredient avocadoGuacamole = new Ingredient();
        avocadoGuacamole.setDescription("avocado");
        avocadoGuacamole.setUom(unit);
        avocadoGuacamole.setAmount(new BigDecimal(2));

        Ingredient freshLimeGuacamole = new Ingredient();
        freshLimeGuacamole.setDescription("fresh lime");
        freshLimeGuacamole.setUom(tablespoon);
        freshLimeGuacamole.setAmount(new BigDecimal(1));

        Ingredient mincedRedOnionGuacamole = new Ingredient();
        mincedRedOnionGuacamole.setDescription("minced red onion");
        mincedRedOnionGuacamole.setUom(tablespoon);
        mincedRedOnionGuacamole.setAmount(new BigDecimal(4));

        Ingredient silantroGuacamole = new Ingredient();
        silantroGuacamole.setDescription("cilantro");
        silantroGuacamole.setUom(tablespoon);
        silantroGuacamole.setAmount(new BigDecimal(2));

        Ingredient groundBlackPepperGuacamole = new Ingredient();
        groundBlackPepperGuacamole.setDescription("ground black pepper");
        groundBlackPepperGuacamole.setUom(pinch);
        groundBlackPepperGuacamole.setAmount(new BigDecimal(1));

        Ingredient ripeTomatoGuacamole = new Ingredient();
        ripeTomatoGuacamole.setDescription("ripe tomato, chopped");
        ripeTomatoGuacamole.setUom(unit);
        ripeTomatoGuacamole.setAmount(new BigDecimal("0.5"));

        Ingredient tortillaChipsGuacamole = new Ingredient();
        tortillaChipsGuacamole.setDescription("Tortilla chips");
        tortillaChipsGuacamole.setUom(some);
        tortillaChipsGuacamole.setAmount(new BigDecimal("0.5"));

        Set<Ingredient> guacamoleIngredients = new HashSet<>();
        guacamoleIngredients.add(avocadoGuacamole);
        guacamoleIngredients.add(freshLimeGuacamole);
        guacamoleIngredients.add(mincedRedOnionGuacamole);
        guacamoleIngredients.add(silantroGuacamole);
        guacamoleIngredients.add(groundBlackPepperGuacamole);
        guacamoleIngredients.add(ripeTomatoGuacamole);
        guacamoleIngredients.add(tortillaChipsGuacamole);

        perfectGuacamole.getIngredients().addAll(guacamoleIngredients);
        guacamoleIngredients.forEach(ingredient -> {
            ingredient.setRecipe(perfectGuacamole);
        });

        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setCookTime(10);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setSource("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setDirections("\n" +
                "Cut the avocados:\n" +
                "\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "How to make guacamole - scoring avocado\n" +
                "Elise Bauer\n" +
                "Mash the avocado flesh:\n" +
                "\n" +
                "Using a fork, roughly mash the avocado. Don't overdo it! The guacamole should be a little chunky.\n" +
                "How to make guacamole - smashing avocado with fork\n" +
                "Elise Bauer\n" +
                "Add the remaining ingredients to taste:\n" +
                "\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "Serve immediately:\n" +
                "\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jicama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days. ");

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("Note: Chilling tomatoes dulls their flavor. So, if you want to add chopped tomato to your guacamole, add just before serving. ");
        perfectGuacamole.setNotes(guacamoleNotes);

        recipeRepository.save(perfectGuacamole);

        //taco recipe
        Recipe tacosRecipe = new Recipe();

        categorySet.add(categoryRepository.findByDescription("American").get());
        tacosRecipe.setCategories(categorySet);

        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        tacoNotes.setRecipe(tacosRecipe);
        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.getIngredients().add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tablespoon, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoon, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoon, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Sugar", new BigDecimal(1), teaspoon, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Salt", new BigDecimal(".5"), teaspoon, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), unit, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("finely grated orange zestr", new BigDecimal(1), tablespoon, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoon, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Olive Oil", new BigDecimal(2), tablespoon, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("boneless chicken thighs", new BigDecimal(4), tablespoon, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("small corn tortillasr", new BigDecimal(8), unit, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("packed baby arugula", new BigDecimal(3), cup, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), unit, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("radishes, thinly sliced", new BigDecimal(4), unit, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pint, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), unit, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), unit, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cup, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("lime, cut into wedges", new BigDecimal(4), unit, tacosRecipe));

        tacosRecipe.setCategories(categorySet);
        recipeRepository.save(tacosRecipe);
    }
}
