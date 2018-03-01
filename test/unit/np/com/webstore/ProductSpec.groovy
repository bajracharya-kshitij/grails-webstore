package np.com.webstore

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Product)
class ProductSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test create product"() {
        given: "a new product"
        Product product = new Product(name: 'Product 1', description: 'Product 1 description', unitPrice: 10, unitsInStock: 100)

        when: "product is validated"
        product.validate()

        then: "product can be saved"
        product.save()
        assert Product.count == 1
    }

    void "test create product with no name"() {
        given: "a new product"
        Product product = new Product(description: 'Product 1 description', unitPrice: 10, unitsInStock: 100)

        when: "product is validated"
        product.validate()

        then: "product has errors"
        assert product.hasErrors()
        assert product.errors.allErrors.size() == 1

        def error = product.errors.allErrors.get(0)
        assert error.field == 'name'
    }

    void "test create product with no description"() {
        given: "a new product"
        Product product = new Product(name: 'Product 1', unitPrice: 10, unitsInStock: 100)

        when: "product is validated"
        product.validate()

        then: "product can be saved"
        product.save()
        assert Product.count == 1
    }

    void "test create product with no unitPrice"() {
        given: "a new product"
        Product product = new Product(name: 'Product 1', description: 'Product 1 description', unitsInStock: 100)

        when: "product is validated"
        product.validate()

        then: "product has errors"
        assert product.hasErrors()
        assert product.errors.allErrors.size() == 1

        def error = product.errors.allErrors.get(0)
        assert error.field == 'unitPrice'
    }

    void "test create product with no unitsInStock"() {
        given: "a new product"
        Product product = new Product(name: 'Product 1', description: 'Product 1 description', unitPrice: 10)

        when: "product is validated"
        product.validate()

        then: "product has errors"
        assert product.hasErrors()
        assert product.errors.allErrors.size() == 1

        def error = product.errors.allErrors.get(0)
        assert error.field == 'unitsInStock'
    }

    void "test create product with negative unitPrice"() {
        given: "a new product"
        Product product = new Product(name: 'Product 1', description: 'Product 1 description', unitPrice: -10, unitsInStock: 100)

        when: "product is validated"
        product.validate()

        then: "product has errors"
        assert product.hasErrors()
        assert product.errors.allErrors.size() == 1

        def error = product.errors.allErrors.get(0)
        assert error.field == 'unitPrice'
        assert error.code == 'min.not.met'
    }

    void "test create product with negative unitsInStock"() {
        given: "a new product"
        Product product = new Product(name: 'Product 1', description: 'Product 1 description',  unitPrice: 10, unitsInStock: -100)

        when: "product is validated"
        product.validate()

        then: "product has errors"
        assert product.hasErrors()
        assert product.errors.allErrors.size() == 1

        def error = product.errors.allErrors.get(0)
        assert error.field == 'unitsInStock'
        assert error.code == 'min.not.met'
    }
}
