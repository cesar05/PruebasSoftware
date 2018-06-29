import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'filterVehiculo',
    pure: false
})

export class FilterVehiculo implements PipeTransform {
    transform(items: any[], filter: Object): any {
        if (!items || !filter) {
            return items;
        }
        return items.filter(item => item.placa.indexOf(filter) !== -1);
    }
}