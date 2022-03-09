import { Component, OnInit } from '@angular/core';

import { Product } from "../product";
import { CategoriesService } from "../categories.service";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: Product[];
  constructor(private categoriesService: CategoriesService) { }

  ngOnInit(): void {
    // this.constUrl = this.product.img;
    this.getProducts();
  }
  getProducts(): void {
    this.categoriesService.getProducts()
        .subscribe(products => this.products = products);
  }

}
