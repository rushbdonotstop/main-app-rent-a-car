import { Bundle } from './Bundle'
import { Request } from './Request'
import { DetailedCart } from './DetailedCart'

export class Cart {

    requests: Request[] = []
    bundles: Bundle[] = []

    constructor(detailedCart: DetailedCart, userId: number) {

        var requests = []
        var bundles = []

        for (let request of detailedCart.requests) {
            var req = new Request(request)
            req.userId = userId
            req.startDate = request.startDate
            req.endDate = request.endDate
            requests.push(req)
        }
        for (let b of detailedCart.bundles) {
            var bundle = new Bundle()
            for (let r of b.requests) {
                var req = new Request(r)
                req.userId = userId
                req.startDate = r.startDate
                req.endDate = r.endDate
                bundle.requests.push(req)
            }
            bundles.push(bundle)
        }

        this.requests = requests
        this.bundles = bundles
    }

}