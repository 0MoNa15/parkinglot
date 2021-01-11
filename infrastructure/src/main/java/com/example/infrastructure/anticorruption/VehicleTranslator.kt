package com.example.infrastructure.anticorruption

import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.entity.LicensePlate
import com.example.infrastructure.database.entity.CarEntity
import com.example.infrastructure.database.entity.MotorcycleEntity

class VehicleTranslator {

    companion object {
        fun fromModelToEntityCar(car: Car): CarEntity {
            return CarEntity(
                car.plateLicensePlate.numberAndLetters,
                car.plateLicensePlate.city,
                car.state,
                car.dateOfAdmission
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
                motorcycle.state,
                motorcycle.dateOfAdmission,
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
                motorcycleEntity.state,
                motorcycleEntity.dateOfAdmission,
                motorcycleEntity.cylinderCapacity
            )
        }

        private fun fromEntityToModelCar(carEntity: CarEntity): Car {
            val mLicense = LicensePlate(carEntity.licensePlate, carEntity.licensePlateCity)

            return Car(
                mLicense,
                carEntity.state,
                carEntity.dateOfAdmission
            )
        }
    }
}