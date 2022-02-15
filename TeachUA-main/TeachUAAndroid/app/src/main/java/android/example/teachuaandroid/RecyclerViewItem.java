package android.example.teachuaandroid;

import android.widget.Button;

public class RecyclerViewItem {
    private int imageResource;
    private String textInfoAboutGroups;
    private Button button;
    private String description;

    public RecyclerViewItem(int imageResource, String textInfoAboutGroups, Button button, String description) {
        this.imageResource = imageResource;
        this.textInfoAboutGroups = textInfoAboutGroups;
        this.button = button;
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTextInfoAboutGroups() {
        return textInfoAboutGroups;
    }

    public void setTextInfoAboutGroups(String textInfoAboutGroups) {
        this.textInfoAboutGroups = textInfoAboutGroups;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
