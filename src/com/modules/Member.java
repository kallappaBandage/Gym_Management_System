package com.modules;

public class Member {
    private int id;
    private String name;
    private String contact;
    private String address; // Ensure this field is included
    private double feePaid;
    private double feeRemaining;

    // Constructor
    public Member(int id, String name, String contact, String address, double feePaid, double feeRemaining) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.feePaid = feePaid;
        this.feeRemaining = feeRemaining;
    }

    // Getters and setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public double getFeePaid() { return feePaid; }
    public void setFeePaid(double feePaid) { this.feePaid = feePaid; }
    public double getFeeRemaining() { return feeRemaining; }
    public void setFeeRemaining(double feeRemaining) { this.feeRemaining = feeRemaining; }
}
