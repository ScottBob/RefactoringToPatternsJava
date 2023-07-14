package MyWork;

import javax.swing.text.html.HTML;

public class OrdersWriter {
    private Orders orders;

    public OrdersWriter(Orders orders) {
        this.orders = orders;
    }

    public String getContents()
    {
        StringBuilder xml = new StringBuilder();
        writeOrderTo(xml);
        return xml.toString();
    }
    public String getTagContents()
    {
        TagBuilder tagBuilder = new TagBuilder("orders");
        writeOrderTo(tagBuilder);
        return tagBuilder.toXml();
    }

    private void writeOrderTo(StringBuilder xml) {
        TagNode ordersTag = new TagNode("orders");
        for (int i = 0; i < this.orders.orderCount(); i++)
        {
            Order order = this.orders.getOrder(i);
            TagNode orderTag = new TagNode("order");
            orderTag.addAttribute("id", String.valueOf(order.getOrderId()));
            writeProductsTo(orderTag, order);
            ordersTag.add(orderTag);
        }
        xml.append(ordersTag.toString());
    }

    private void writeOrderTo(TagBuilder tagBuilder) {

        for (int i = 0; i < this.orders.orderCount(); i++)
        {
            Order order = this.orders.getOrder(i);
            tagBuilder.addToParent("orders", "order");
            tagBuilder.addAttribute("id", String.valueOf(order.getOrderId()));
            writeProductsTo(tagBuilder, order);
        }
    }

    private void writeProductsTo(TagNode orderTag, Order order) {
        for (int j = 0; j < order.productCount(); j++) {
            Product product = order.Product(j);
            TagNode productTag = new TagNode("product");
            productTag.addAttribute("id", "" + product.getId());
            productTag.addAttribute("color", this.colorFor(product));
            if (product.getSize() != ProductSize.NotApplicable)
            {
                productTag.addAttribute("size", this.sizeFor(product));
            }

            productTag.addValue(product.getName());
            writePriceTo(productTag, product);
            orderTag.add(productTag);
        }
    }

    private void writeProductsTo(TagBuilder tagBuilder, Order order) {
        for (int j = 0; j < order.productCount(); j++) {
            tagBuilder.addToParent("order","product");
            Product product = order.Product(j);
            tagBuilder.addAttribute("id", String.valueOf(product.getId()));
            tagBuilder.addAttribute("color", this.colorFor(product));
            if (product.getSize() != ProductSize.NotApplicable)
            {
                tagBuilder.addAttribute("size", this.sizeFor(product));
            }
            tagBuilder.addValue(product.getName());

            writePriceTo(tagBuilder, product);
        }
    }

    private void writePriceTo(TagNode productTag, Product product) {
        TagNode priceTag = new TagNode("price");
        priceTag.addAttribute("currency", currencyFor(product));
        priceTag.addValue(product.getPrice());
        productTag.add(priceTag);
    }

    private TagNode tagPriceWriterCaller(TagNode productTag, Product product) {
        TagBuilder tag = new TagBuilder("product");
        writePriceTo(tag, product);
        return tag.getRootNode();
    }

    private void writePriceTo(TagBuilder tagBuilder, Product product) {
        tagBuilder.addToParent("product", "price");
        tagBuilder.addAttribute("currency", currencyFor(product));
        tagBuilder.addValue(product.getPrice());
    }

    private String currencyFor(Product product) {
        // I made the assumption that all products will be in USD for
        // this example
        return "USD";
    }

    private String sizeFor(Product product) {
        // I've made the assumption that all sizes will be a medium
        // for this example
        switch (product.getSize())
        {
            case Medium:
                return "medium";
            default:
                return "NOT APPLICABLE";
        }
    }

    private String colorFor(Product product) {
        // I made the assumption that all products are red for this example
        return "red";
    }
}
