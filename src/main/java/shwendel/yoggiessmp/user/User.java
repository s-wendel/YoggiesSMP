package shwendel.yoggiessmp.user;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class User {

    private Player player;
    private int yoggiesFragments; // Amount of Yoggies Fragments received
    private int donationRemainder; // The remainder after donating
    private ItemStack[] donationRemnants; // The donation GUI

    public User(Player player, int yoggiesFragments, int donationRemainder, ItemStack[] donationRemnants) {
        this.player = player;
        this.yoggiesFragments = yoggiesFragments;
        this.donationRemainder = donationRemainder;
        this.donationRemnants = donationRemnants;
    }

    public Player getPlayer() {
        return player;
    }

    public void setYoggiesFragments(int yoggiesFragments) {
        this.yoggiesFragments = yoggiesFragments;
    }

    public int getYoggiesFragments() {
        return yoggiesFragments;
    }

    public void setDonationRemainder(int donationRemainder) {
        this.donationRemainder = donationRemainder;
    }

    public int getDonationRemainder() {
        return donationRemainder;
    }

    public void setDonationRemnants(ItemStack[] donationRemnants) {
        this.donationRemnants = donationRemnants;
    }

    public ItemStack[] getDonationRemnants() {
        return donationRemnants;
    }

}
