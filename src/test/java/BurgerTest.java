import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    private Burger burger;

    @Before
    public void setUp(){
        burger = new Burger();
    }

    @Test
    public void setBunsTest(){
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest(){
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest(){
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientsTest(){
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest(){
        when(bun.getPrice()).thenReturn(1.0f);
        when(ingredient1.getPrice()).thenReturn(0.5f);
        when(ingredient2.getPrice()).thenReturn(0.75f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        assertEquals(3.25f, burger.getPrice(), 0.001);
    }

    @Test
    public void getReceiptTest() {
        when(bun.getName()).thenReturn("Булочка");
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("Сырный");
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getName()).thenReturn("Кетчуп");

        when(bun.getPrice()).thenReturn(1.0f);
        when(ingredient1.getPrice()).thenReturn(0.5f);
        when(ingredient2.getPrice()).thenReturn(1.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String expectedReceipt =
                String.format("(==== Булочка ====)%n") +
                        String.format("= sauce Сырный =%n") +
                        String.format("= filling Кетчуп =%n") +
                        String.format("(==== Булочка ====)%n") +
                        String.format("%nPrice: %f%n", 3.5f);

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}

