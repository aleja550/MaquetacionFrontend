import { Component, signal } from '@angular/core';
import { Router } from '@angular/router';

interface Reminder {
  id: number;
  title: string;
  description: string;
  category: string;
  date: string;
  time: string;
  recurrence: string;
  status: 'Activo' | 'Completado' | 'Perdido' | 'Programado';
}

@Component({
  selector: 'app-recordatorios',
  imports: [],
  templateUrl: './recordatorios.html',
  styleUrl: './recordatorios.css'
})
export class Recordatorios {
  constructor(private router: Router) {}

  protected readonly reminders = signal<Reminder[]>([
    { id: 1, title: 'Ejercicio matutino', description: '30 minutos de cardio', category: 'Ejercicio', date: 'Hoy', time: '6:00 AM', recurrence: 'Diario', status: 'Activo' },
    { id: 2, title: 'Tomar medicamento', description: 'Vitamina D', category: 'Salud', date: 'Hoy', time: '2:00 PM', recurrence: 'Diario', status: 'Activo' },
    { id: 3, title: 'Llamar a mamá', description: 'Llamada semanal', category: 'Personal', date: 'Hoy', time: '6:00 PM', recurrence: 'Semanal', status: 'Programado' },
    { id: 4, title: 'Estudiar Angular', description: 'Revisar documentación', category: 'Estudios', date: 'Ayer', time: '8:00 PM', recurrence: 'Diario', status: 'Completado' },
    { id: 5, title: 'Reunión trabajo', description: 'Standup diario', category: 'Trabajo', date: 'Ayer', time: '9:00 AM', recurrence: 'Diario', status: 'Perdido' }
  ]);

  protected readonly stats = signal({
    completed: 12,
    active: 4,
    missed: 2,
    scheduled: 4,
    completionRate: 85
  });

  protected onEditReminder(reminder: Reminder): void {
    console.log('Edit reminder:', reminder);
  }

  protected onDeleteReminder(reminder: Reminder): void {
    console.log('Delete reminder:', reminder);
  }

  protected onDuplicateReminder(reminder: Reminder): void {
    console.log('Duplicate reminder:', reminder);
  }

  protected onNewReminder(): void {
    this.router.navigate(['/crear-recordatorio']);
  }

  protected getStatusColor(status: string): string {
    switch (status) {
      case 'Activo': return 'text-green-600';
      case 'Completado': return 'text-blue-600';
      case 'Perdido': return 'text-red-600';
      case 'Programado': return 'text-orange-600';
      default: return 'text-gray-600';
    }
  }
}
