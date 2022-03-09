import { Category } from "./category";
import { computerProducts } from "./computer-products";
import { homeProducts } from "./home-produts";
import { phoneProducts } from "./phone-products";
import { swearProduct } from "./swear-products";

export const categories: Category[] = [
    {
        id: 1, 
        name: 'Phone Products',
        products: phoneProducts
    },
    {
        id: 2, 
        name: 'Computer Products',
        products: computerProducts
    },
    {
        id: 3, 
        name: 'Swear Products',
        products: swearProduct
    },
    {
        id: 4, 
        name: 'Home Products',
        products: homeProducts
    }
]
