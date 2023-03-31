package MyWork.Descriptors;

import MyWork.Mappers.DescriptorMapper;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;

public class AttributeDescriptor {
    public final String DescriptorName;
    private final Type mapperType;
    private final Type forType;

    AttributeDescriptor(String descriptorName, Type mapperType, Type forType)
    {
        this.DescriptorName = descriptorName;
        this.mapperType = mapperType;
        this.forType = forType;
    }

    public static AttributeDescriptor forDate(String descriptorName, Type type) {
        return new DefaultDescriptor(descriptorName, DescriptorMapper.class, ZonedDateTime.class);
    }

    public static AttributeDescriptor forInteger(String descriptorName, Type type) {
        return new DefaultDescriptor(descriptorName, DescriptorMapper.class, Integer.class);
    }
}
