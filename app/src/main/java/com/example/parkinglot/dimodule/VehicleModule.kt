package com.example.parkinglot.dimodule

import com.example.domain.vehicle.repository.VehicleRepository
import com.example.infrastructure.repository.VehicleRepositoryRoom
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class VehicleModule {
    @Binds
    abstract fun bindVehicleRepository(parkingImplementationMotorbike: VehicleRepositoryRoom): VehicleRepository
}