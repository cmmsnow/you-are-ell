package views;

import models.Id;

public class IdTextView {
    Id idToDisplay;

    public IdTextView(Id idToDisplay) {
        this.idToDisplay = idToDisplay;
    }

    @Override
    public String toString() {
        return String.format("\nUserid: %s, Name: %s, Github: %s", idToDisplay.getUserid(), idToDisplay.getName(), idToDisplay.getGithub());
    }
}