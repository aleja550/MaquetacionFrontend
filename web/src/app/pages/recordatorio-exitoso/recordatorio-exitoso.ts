import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recordatorio-exitoso',
  imports: [],
  templateUrl: './recordatorio-exitoso.html',
  styleUrl: './recordatorio-exitoso.css'
})
export class RecordatorioExitoso {
  constructor(private router: Router) {}

  protected onViewReminders(): void {
    this.router.navigate(['/recordatorios']);
  }

  protected onCreateAnother(): void {
    this.router.navigate(['/crear-recordatorio']);
  }
}
