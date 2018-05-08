package udacity.cmtruong.com.caketime.model;

import java.util.List;

/**
 * Cake model class in MVVM pattern
 *
 * @author davidetruong
 * @version 1.0
 * @since May, 8th
 */
public class Cake {
    private int id;
    private String name;
    private List<Ingredient> ingredients;
    private List<Step> steps;
    private int serving;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Cake{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                ", serving=" + serving +
                ", image='" + image + '\'' +
                '}';
    }
}
