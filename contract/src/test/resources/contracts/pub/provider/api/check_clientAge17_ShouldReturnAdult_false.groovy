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
                age: $(regex("[0-1][0-8]"))
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 403
        body("""
			{"adult":false,"blocked":false,"debtor":false,"debt":0}
			""")
        headers {
            contentType(applicationJson())
        }
    }
}

