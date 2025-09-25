import { Component, signal } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-crear-categoria',
  imports: [],
  templateUrl: './crear-categoria.html',
  styleUrl: './crear-categoria.css'
})
export class CrearCategoria {
  protected readonly name = signal('');
  protected readonly sound = signal('');
  protected readonly selectedIcon = signal('');
  protected readonly selectedColor = signal('#4DCB3F');
  protected readonly vibration = signal('suave');

  protected readonly icons = [
    { name: 'favorite', icon: '❤️' },
    { name: 'edit', icon: '✏️' },
    { name: 'bus', icon: '🚌' },
    { name: 'light', icon: '☀️' }
  ];

  protected readonly colors = [
    '#4DCB3F', '#343534', '#EC7D24', '#C6C6C7'
  ];

  constructor(private router: Router) {}

  protected onCancel(): void {
    this.router.navigate(['/categorias']);
  }

  protected onCreate(): void {
    console.log('Creating category:', {
      name: this.name(),
      sound: this.sound(),
      icon: this.selectedIcon(),
      color: this.selectedColor(),
      vibration: this.vibration()
    });
    this.router.navigate(['/categoria-exitosa']);
  }

  protected onIconSelect(icon: string): void {
    this.selectedIcon.set(icon);
  }

  protected onColorSelect(color: string): void {
    this.selectedColor.set(color);
  }

  protected onVibrationChange(type: string): void {
    this.vibration.set(type);
  }
}
