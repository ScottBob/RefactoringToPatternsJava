package MyWork.Mappers;

import MyWork.Descriptors.AttributeDescriptor;
import MyWork.Descriptors.DefaultDescriptor;
import MyWork.Descriptors.ReferenceDescriptor;
import MyWork.Domain.User;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class DescriptorMapper {
    protected List<AttributeDescriptor> createAttributeDescriptors() {
        List<AttributeDescriptor> result = new ArrayList<>();

        result.add(AttributeDescriptor.forInteger("remoteId", GetClass()));
        result.add(new DefaultDescriptor("createdDate", GetClass(), ZonedDateTime.class));
        result.add(new DefaultDescriptor("lastChangedDate", GetClass(), ZonedDateTime.class));
        result.add(new ReferenceDescriptor("createdBy", GetClass(), User.class));
        result.add(new ReferenceDescriptor("lastChangedBy", GetClass(), User.class));
        result.add(AttributeDescriptor.forInteger("optimisticLockVersion", GetClass()));
        return result;
    }

    private Type GetClass()
    {
        return DescriptorMapper.class;
    }
}
