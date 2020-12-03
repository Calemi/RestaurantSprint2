package order;

public enum Food {

    BURGER_CLASSIC      ("Classic Burger", 8.99F),
    BURGER_MUSH_STEAK   ("Mushroom Burger", 9.99F),
    BURGER_HICKORY      ("Hickory Burger", 9.99F),
    BURGER_JALAPENO     ("Jalapeno Burger", 8.50F),

    SALAD_CLASSIC       ("Classic Salad", 10.99F),
    SALAD_BUFFALO       ("Buffalo Salad", 11.99F),
    SALAD_COBB          ("Cobb Salad", 13.99F),
    SALAD_HICKORY       ("Hickory Salad", 11.99F),

    SOUP_CLAM_CHOWDER   ("Clam Chowder", 6.99F),
    SOUP_ONION          ("French Onion Soup", 6.99F),
    SOUP_CHICKEN_TORT   ("Chicken Tort Soup", 6.99F),
    SOUP_LOBSTER_BISQUE ("Bisque", 6.99F),

    SIDE_FRIES          ("French Fries", 2.99F),
    SIDE_BROCCOLI       ("Broccoli", 2.99F),
    SIDE_OKRA           ("Fried Okra", 2.99F),
    SIDE_BEANS          ("Baked Beans", 3.99F),

    DRINK_COLA          ("Cola", 1.99F),
    DRINK_GINGER_ALE    ("Ginger Ale", 1.99F),
    DRINK_BEER          ("Beer", 3.99F),
    DRINK_RED_WINE      ("Red Wine", 6.99F);

    private String name;
    private float price;

    Food(String name, float price) {
        this.name = name;
        this.price = price;
    }
}
