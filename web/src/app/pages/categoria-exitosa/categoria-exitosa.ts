import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-categoria-exitosa',
  imports: [],
  templateUrl: './categoria-exitosa.html',
  styleUrl: './categoria-exitosa.css'
})
export class CategoriaExitosa {
  constructor(private router: Router) {}

  protected onViewCategories(): void {
    this.router.navigate(['/categorias']);
  }

  protected onCreateAnother(): void {
    this.router.navigate(['/crear-categoria']);
  }
}
