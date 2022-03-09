import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Product } from "../product";
import { CategoriesService } from "../categories.service";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  product: Product;
  i:number =1;
  url:string;
  constUrl:string;
  constructor(
    private route: ActivatedRoute,
    private categoriesService: CategoriesService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getProduct();

    this.url = this.constUrl;
    console.log(this.url)
    this.url += this.i + '.jpg';
    this.product.img += '1.jpg'
  }

  getProduct(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.categoriesService.getProduct(id)
      .subscribe(product => this.product = product);
  }
  goBack(): void {
    this.location.back();
  }
}
