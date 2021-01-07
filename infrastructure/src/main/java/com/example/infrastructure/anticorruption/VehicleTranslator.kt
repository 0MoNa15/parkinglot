package com.example.infrastructure.anticorruption

import com.example.domain.vehicles.aggregates.Car
import com.example.domain.vehicles.aggregates.Motorcycle
import com.example.domain.vehicles.entities.LicensePlate
import com.example.infrastructure.database.entities.CarEntity
import com.example.infrastructure.database.entities.MotorcycleEntity

class VehicleTranslator {

    companion object {
        fun fromModelToEntityCar(car: Car): CarEntity {
            return CarEntity(
                car.plateLicensePlate.numberAndLetters,
                car.plateLicensePlate.city,
                car.model,
                car.color,
                car.state
            )
        }

        fun fromListCarEntityToListCarModel(listCarEntity: List<CarEntity>): ArrayList<Car> {
            val arrayListCar = ArrayList<Car>()
            listCarEntity.forEach {
                val car = fromEntityToModelCar(it)
                arrayListCar.add(car)
            }
            return arrayListCar
        }

        fun fromModelToEntityMotorcycle(motorcycle: Motorcycle): MotorcycleEntity {
            return MotorcycleEntity(
                motorcycle.plateLicensePlate.numberAndLetters,
                motorcycle.plateLicensePlate.city,
                motorcycle.model,
                motorcycle.color,
                motorcycle.state,
                motorcycle.cylinderCapacity
            )
        }

        fun fromListMotorcycleEntityToListMotorcycleModel(listMotorcycleEntity: List<MotorcycleEntity>): ArrayList<Motorcycle> {
            val arrayListMotorcycle = ArrayList<Motorcycle>()
            listMotorcycleEntity.forEach {
                val motorcycle = fromEntityToModelMotorcycle(it)
                arrayListMotorcycle.add(motorcycle)
            }
            return arrayListMotorcycle
        }

        private fun fromEntityToModelMotorcycle(motorcycleEntity: MotorcycleEntity): Motorcycle {
            val mLicense = LicensePlate(motorcycleEntity.licensePlate, motorcycleEntity.licensePlateCity)

            return Motorcycle(
                mLicense,
                motorcycleEntity.model,
                motorcycleEntity.color,
                motorcycleEntity.state,
                motorcycleEntity.cylinderCapacity
            )
        }

        private fun fromEntityToModelCar(carEntity: CarEntity): Car {
            val mLicense = LicensePlate(carEntity.licensePlate, carEntity.licensePlateCity)

            return Car(
                mLicense,
                carEntity.model,
                carEntity.color,
                carEntity.state)
        }
    }
}