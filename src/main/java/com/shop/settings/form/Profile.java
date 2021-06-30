package com.shop.settings.form;

import com.shop.domain.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

@NamedEntityGraph(name = "account.withOrdersProductAndProduct", attributeNodes = {
        @NamedAttributeNode("ordersProduct"),
        @NamedAttributeNode("product")})

@Data
@NoArgsConstructor
public class Profile {

    @Length(max = 35)
    private String bio;
//    private String url;
//    private String occupation;
//    private String location;
    
    private String profileImage;

    public Profile(Account account) {
        this.bio = account.getBio();
//        this.url = account.getUrl();
//        this.occupation = account.getOccupation();
//        this.location = account.getLocation();
        this.profileImage = account.getProfileImage();
    }

}
