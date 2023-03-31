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
            xml.append("<product");
            xml.append(" id='");
            xml.append(product.getId());
            xml.append("'");
            xml.append(" color='");
            xml.append(this.colorFor(product));
            xml.append("'");
            if (product.getSize() != ProductSize.NotApplicable)
            {
                xml.append(" size='");
                xml.append(this.sizeFor(product));
                xml.append("'");
            }

            xml.append(">");
            writePriceTo(xml, product);
            xml.append(product.getName());
            xml.append("</product>");
        }
    }

    private void writePriceTo(StringBuilder xml, Product product) {
        TagNode priceNode = new TagNode("price");
        priceNode.addAttribute("currency", currencyFor(product));
        priceNode.addValue(product.getPrice());
        xml.append(priceNode.toString());
        /*
        xml.append("<price");
        xml.append(" currency='");
        xml.append(this.currencyFor(product));
        xml.append("'>");
        xml.append(product.getPrice());
        xml.append("</price>");
         */
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
