package com.example.parkinglot.dimodule

import com.example.domain.vehicle.repository.CarRepository
import com.example.domain.vehicle.repository.MotorcycleRepository
import com.example.infrastructure.repository.CarRoom
import com.example.infrastructure.repository.MotorcycleRoom
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class VehicleDiModule {
    @Binds
    abstract fun bindCarRepository(parkingImplementationMotorbike: CarRoom): CarRepository

    @Binds
    abstract fun bindMotorcycleRepository(parkingImplementationMotorbike: MotorcycleRoom): MotorcycleRepository
}