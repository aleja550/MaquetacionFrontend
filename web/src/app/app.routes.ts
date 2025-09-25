import { Routes } from '@angular/router';
import { Dashboard } from './pages/dashboard/dashboard';
import { Categorias } from './pages/categorias/categorias';
import { Recordatorios } from './pages/recordatorios/recordatorios';
import { Configuracion } from './pages/configuracion/configuracion';
import { CrearRecordatorio } from './pages/crear-recordatorio/crear-recordatorio';
import { RecordatorioExitoso } from './pages/recordatorio-exitoso/recordatorio-exitoso';
import { CrearCategoria } from './pages/crear-categoria/crear-categoria';
import { CategoriaExitosa } from './pages/categoria-exitosa/categoria-exitosa';
import { AlarmaMatematica } from './pages/alarma-matematica/alarma-matematica';
import { AlarmaMotivacional } from './pages/alarma-motivacional/alarma-motivacional';

export const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: Dashboard },
  { path: 'categorias', component: Categorias },
  { path: 'recordatorios', component: Recordatorios },
  { path: 'configuracion', component: Configuracion },
  { path: 'crear-recordatorio', component: CrearRecordatorio },
  { path: 'recordatorio-exitoso', component: RecordatorioExitoso },
  { path: 'crear-categoria', component: CrearCategoria },
  { path: 'categoria-exitosa', component: CategoriaExitosa },
  { path: 'alarma-matematica', component: AlarmaMatematica },
  { path: 'alarma-motivacional', component: AlarmaMotivacional }
];
