package com.example.demo.utils;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.google.common.base.Splitter;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author joker
 * @date 2022/11/23
 */
public class NameUtils {
    public static <T> String buildCopyPlanName(String sourceName, BaseMapper<T> baseMapper, SFunction<T, Object> function) {
        String temp = sourceName + "_Copy";

        List<T> models = baseMapper.selectList(new LambdaUpdateWrapper<T>().like(function, temp));
        if (CollectionUtils.isEmpty(models)) {
            return temp;
        }
        List<Object> names = models.stream().map(function).collect(Collectors.toList());
        boolean hasMultiCopies = false;
        int maxNum = 0;
        for (Object o : names) {
            String name = o.toString();
            if (StringUtils.equals(temp, name)) {
                continue;
            }
            List<String> likes = Splitter.on("(").splitToList(name);
            if (likes.size() == 2) {
                List<String> nums = Splitter.on(")").splitToList(likes.get(1));
                if (nums.size() == 2) {
                    hasMultiCopies = true;
                    maxNum = Integer.valueOf(nums.get(0)) > maxNum ? Integer.valueOf(nums.get(0)) : maxNum;
                }
            }
        }
        if (!hasMultiCopies) {
            return temp + "(1)";
        }
        return temp + "(" + (maxNum + 1) + ")";
    }
}
