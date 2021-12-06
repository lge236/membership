package testProject.membership.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/*
Spring Data JPA
식별자를 직접 할당하여 관리
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)// 파라미터가 없는 생성자를 생성
@Entity
@javax.persistence.Table(name="product_info")
@Getter
public class ProductInfo{

    @Id // 직접할당
    @javax.persistence.Id //Prime Key
    @Column(name = "product_num", unique = true) //Entity Key?
    @GeneratedValue(strategy= GenerationType.IDENTITY)// DB에 위임을 통해 기본 키 생성
    private Long product_num;

    @Column(name = "product_category")
    private String product_category;

    @Column(name = "product_name", unique = true)
    private String product_name;

    @Column(name = "product_price")
    private int product_price;

    @Column(name = "product_stock")
    private int product_stock;

    @Column(name = "product_detail")
    private String product_detail;

    @Column(name = "product_date")
    private String product_date;



    @Builder
    public ProductInfo(Long product_num, String product_category, String product_name, int product_price, int product_stock, String product_detail, String product_date) {
        this.product_num = product_num;
        this.product_category = product_category;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_stock = product_stock;
        this.product_detail = product_detail;
        this.product_date = product_date;
    }
}