package org.example.service;

public class User {
  private Integer userId;
  private String firstName;
  private String lastName;
  private String companyName;

  public User() {}

  public User(Integer userId, String firstName, String lastName, String companyName) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.companyName = companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Integer getUserId() {
    return userId;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    User other = (User) obj;
    if (userId == null) {
      if (other.userId != null) return false;
    } else if (!userId.equals(other.userId)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    return result;
  }
}
