package com.kunlunsoft.handler.constraint;

import com.string.widget.util.ValueWidget;
import com.kunlunsoft.annotation.ObjColumnEitherHasVal;
import oa.shortCut.LogicExc;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjColumnValidatorHandler implements ConstraintValidator<ObjColumnEitherHasVal, Object> {
    private String[] columns;

    private static Object getColumnVal(Object object, Class clazz, String column) {
        Method m = ReflectionUtils.findMethod(clazz, "get" + ValueWidget.capitalize(column));
        if (null == m) {
            LogicExc.throwEx("5000", "成员变量 " + column + "没有对应的getter 方法");
        }
        Object val = null;
        try {
            val = m.invoke(object, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return val;
    }

    @Override
    public void initialize(ObjColumnEitherHasVal constraintAnnotation) {
        //获取设置的字段值
        this.columns = constraintAnnotation.columns();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        //判断参数是否等于设置的字段值，返回结果
        if (ValueWidget.isNullOrEmpty(object)) {
            return false;
        }
        if (ValueWidget.isNullOrEmpty(this.columns)) {
            return true;
        }
        Class clazz = object.getClass();
        int size = columns.length;
        for (int i = 0; i < size; i++) {
            Object val = getColumnVal(object, clazz, columns[i]);
            if (!ValueWidget.isNullOrEmpty(val)) {
                return true;
            }
        }
//        context.buildConstraintViolationWithTemplate("aa");
        return false;
    }

}
