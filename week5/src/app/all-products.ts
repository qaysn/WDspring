import { Product } from "./product";
import { computerProducts } from "./computer-products";
import { homeProducts } from "./home-produts";
import { phoneProducts } from "./phone-products";
import { swearProduct } from "./swear-products";

export const products: Product[] = [
]
phoneProducts.forEach(addProduct);
computerProducts.forEach(addProduct);
homeProducts.forEach(addProduct);
swearProduct.forEach(addProduct);

function addProduct(product:Product):void {
    products.push(product);
}