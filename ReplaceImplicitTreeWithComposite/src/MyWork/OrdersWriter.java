package MyWork;

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

    private void writeOrderTo(StringBuilder xml) {
        xml.append("<orders>");
        for (int i = 0; i < this.orders.orderCount(); i++)
        {
            Order order = this.orders.getOrder(i);
            xml.append("<order");
            xml.append(" id='");
            xml.append(order.getOrderId());
            xml.append("'>");
            writeProductsTo(xml, order);

            xml.append("</order>");
        }

        xml.append("</orders>");
    }

    private void writeProductsTo(StringBuilder xml, Order order) {
        for (int j = 0; j < order.productCount(); j++) {
            Product product = order.Product(j);
            TagNode productTag = new TagNode("product");
            productTag.addAttribute("id", "" + product.getId());
            productTag.addAttribute("color", this.colorFor(product));
            if (product.getSize() != ProductSize.NotApplicable)
            {
                productTag.addAttribute("size", this.sizeFor(product));
            }

            writePriceTo(productTag, product);
            productTag.addValue(product.getName());
            xml.append(productTag.toString());
        }
    }

    private void writePriceTo(TagNode productTag, Product product) {
        TagNode priceTag = new TagNode("price");
        priceTag.addAttribute("currency", currencyFor(product));
        priceTag.addValue(product.getPrice());
        productTag.add(priceTag);
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
