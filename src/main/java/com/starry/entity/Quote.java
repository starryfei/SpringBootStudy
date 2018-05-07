import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * @ClassName  Quote   
 * @Description TODO(这里用一句话描述这个类的作用)   
 * @author yafei.qin 
 * @date 2018年5月7日 下午5:13:43   
 *     
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private String type;
    private Value value;

    public Quote() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Quote{" + "type='" + type + '\'' + ", value=" + value + '}';
    }
}
