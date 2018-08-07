package com.kunlunsoft.dto.constraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oa.util.constraint.EnumConstant;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {
    @NotBlank(message = "code不能为空")
    @EnumConstant(message = "verson只能为1.0", value = "1.0")
    String code;

    @Length(max = 10, message = "name长度不能超过10")
    String name;
}
