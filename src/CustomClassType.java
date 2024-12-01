public enum CustomClassType {
    BOOK("КНИГИ"),
    ROOT_VEGETABLE("КОРНЕПЛОДЫ"),
    AUTOMOBILE("АВТОМОБИЛИ");

    private final String name;

    CustomClassType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
