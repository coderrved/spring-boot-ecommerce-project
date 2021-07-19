import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  title = "ÜRÜN LİSTESİ";
  products : any[] = [
  {id: 1, productName: "Laptop", price: 2500, categoryID: 1, description: "HP Laptop"},
  {id: 2, productName: "Masaüstü Bilgisayar", price: 2500, categoryID: 1, description: "HP Bilgisayar"},
  {id: 3, productName: "Klavye", price: 2500, categoryID: 1, description: "HP Klavye"},
  {id: 4, productName: "Mouse", price: 2500, categoryID: 1, description: "HP Mouse"}
  ]
  constructor() { }

  ngOnInit(): void {
  }

}
