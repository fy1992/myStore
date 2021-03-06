package cn.dahe.dto;

import cn.dahe.model.Taste;
import cn.dahe.model.TasteGroup;

import java.util.Set;

/**
 * 口味dto
 * Created by fy on 2017/1/18.
 */
public class TasteDto {
    private int id;
    private String name;
    //是否必选
    private int isRequired;
    //口味选择方式
    private int type;

    private String description;

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

    public int getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(int isRequired) {
        this.isRequired = isRequired;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TasteDto init(TasteGroup tasteGroup){
        TasteDto tasteDto = new TasteDto();
        tasteDto.setId(tasteGroup.getId());
        tasteDto.setName(tasteGroup.getName());
        tasteDto.setIsRequired(tasteGroup.getRequired());
        tasteDto.setType(tasteGroup.getType());
        Set<Taste> tasteSet = tasteGroup.getTasteSet();
        StringBuffer descriptionSb = new StringBuffer();
        for(Taste taste : tasteSet){
            descriptionSb.append(taste.getName() + " ( " + taste.getPrice() + " ) ,");
        }
        String description = descriptionSb.toString();
        description = description.substring(0, description.lastIndexOf(","));
        tasteDto.setDescription(description);
        return tasteDto;
    }

    @Override
    public String toString() {
        return "TasteDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isRequired=" + isRequired +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
