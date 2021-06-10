package contracts.pub.provider.api


import org.springframework.cloud.contract.spec.Contract


Contract.make {

    description("""
Represents a successful scenario of passing check

```
given:
	client is old enough, 21 years
when:
	call check method
then:
	we'll return checkDto and confirmed he is adult
```
""")
    request {
        method POST()
        url '/clients/1/check'
        body(
                age: $(regex("[2-9][0-9]"))
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body("""
			{"adult":true,"blocked":false,"debtor":false,"debt":0}
			""")
        headers {
            contentType(applicationJson())
        }
    }
}

