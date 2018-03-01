package np.com.webstore

class Product {

    String name
    String description
    BigDecimal unitPrice
    Long unitsInStock

    static constraints = {
        name nullable: false, blank: false
        description nullable: true, blank: true
        unitPrice nullable: false, validator: priceValidator
        unitsInStock nullable: false, validator: amountValidator
    }

    def static priceValidator = { value, object ->
        if(value<0) { return ['min.not.met']}
        true
    }

    def static amountValidator = { value, object ->
        if(value<0) { return ['min.not.met']}
        true
    }
}
