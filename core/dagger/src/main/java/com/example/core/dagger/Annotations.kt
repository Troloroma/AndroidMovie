package com.example.core.dagger

import com.example.core.dependency.Dependencies
import dagger.MapKey
import javax.inject.Scope
import kotlin.reflect.KClass

@MapKey
annotation class DependenciesKey(val value: KClass<out Dependencies>)

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureScoped
