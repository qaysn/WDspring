import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';

import { Product } from "./product";  
import { Category } from "./category";
import { categories} from "./mock-categories";
import { products } from './all-products';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor() { }

  getProducts(): Observable<Product[]> {
    return of (products);
  }

  getCategories(): Observable<Category[]> {
    return of (categories);
  }

  getProduct(id: number): Observable<Product> {
    // TODO: send the message _after_ fetching the hero
    return of(products.find(product => product.id === id));
  }

  getCategory(id: number): Observable<Category> {
    // TODO: send the message _after_ fetching the hero
    return of(categories.find(category => category.id === id));
  }
}
