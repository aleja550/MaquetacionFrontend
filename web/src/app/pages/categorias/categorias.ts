import { Component, signal } from '@angular/core';
import { Router } from '@angular/router';

interface Category {
  id: number;
  name: string;
  color: string;
  reminderCount: number;
}

@Component({
  selector: 'app-categorias',
  imports: [],
  templateUrl: './categorias.html',
  styleUrl: './categorias.css'
})
export class Categorias {
  constructor(private router: Router) {}

  protected readonly categories = signal<Category[]>([
    { id: 1, name: 'Salud', color: '#64B546', reminderCount: 5 },
    { id: 2, name: 'Personal', color: '#FF8C00', reminderCount: 3 },
    { id: 3, name: 'Estudios', color: '#8A2BE2', reminderCount: 2 }
  ]);

  protected onEditCategory(category: Category): void {
    console.log('Edit category:', category);
  }

  protected onDeleteCategory(category: Category): void {
    console.log('Delete category:', category);
  }

  protected onNewCategory(): void {
    this.router.navigate(['/crear-categoria']);
  }
}
