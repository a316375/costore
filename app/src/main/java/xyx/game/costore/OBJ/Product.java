package xyx.game.costore.OBJ;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;


@Keep
@Entity
public class Product {
    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String code;
    private String name;
    private String price;
    @Generated(hash = 1604367450)
    public Product(Long id, String code, String name, String price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
    }
    @Generated(hash = 1890278724)
    public Product() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    
}
