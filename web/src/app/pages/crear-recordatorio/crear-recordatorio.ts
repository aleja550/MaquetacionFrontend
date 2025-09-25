import { Component, signal } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-crear-recordatorio',
  imports: [],
  templateUrl: './crear-recordatorio.html',
  styleUrl: './crear-recordatorio.css'
})
export class CrearRecordatorio {
  protected readonly title = signal('');
  protected readonly date = signal('');
  protected readonly time = signal('');
  protected readonly category = signal('');
  protected readonly recurrence = signal('');
  protected readonly notificationType = signal('simple');

  constructor(private router: Router) {}

  protected onCancel(): void {
    this.router.navigate(['/recordatorios']);
  }

  protected onCreate(): void {
    console.log('Creating reminder:', {
      title: this.title(),
      date: this.date(),
      time: this.time(),
      category: this.category(),
      recurrence: this.recurrence(),
      notificationType: this.notificationType()
    });
    this.router.navigate(['/recordatorio-exitoso']);
  }

  protected onNotificationTypeChange(type: string): void {
    this.notificationType.set(type);
  }
}
