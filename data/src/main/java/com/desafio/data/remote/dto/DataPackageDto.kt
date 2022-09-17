package com.desafio.data.remote.dto

import androidx.lifecycle.Transformations.map
import com.desafio.domain.model.DataPackage

sealed class DataPackageDto {
    data class Response(
        var name: String,
        var title: String,
        var description: String,
        var homepage: String,
        var version: String,
        var license: String,
        var sources: List<SourcesDto>,
        var resources: List<ResourcesDto>
    ) : DataPackageDto() {
        fun toDataPackage() = DataPackage.Response(
            name = name,
            title = title,
            description = description,
            homepage = homepage,
            version = version,
            license = license,
            sources = sources.map{ source ->
                source.toSources()
            },
        resources = resources.map { resource ->
            resource.toResources()
        },
        )
    }

    data class SourcesDto(
        var title: String,
        var path: String
    ){
       fun toSources() = DataPackage.Sources(
          title = title,
          path = path
      )
    }

    data class ResourcesDto(
        var name: String,
        var path: String,
        var format: String,
        var mediatype: String,
        var bytes: Int,
        var schema: SchemaDto
    ){
        fun toResources() = DataPackage.Resources(
            name = name,
            path = path,
            format = format,
            mediatype = mediatype,
            bytes = bytes,
            schema = schema.toSchema(),
        )
    }

    data class SchemaDto(
        var fields: List<FieldsDto>
    ){
        fun toSchema() = DataPackage.Schema(
            fields = fields.map {
                it.toFields()
            }
        )
    }

    data class FieldsDto(
        var name: String,
        var type: String,
        var description: String
    ){
        fun toFields() = DataPackage.Fields(
            name = name,
            type = type,
            description = description
        )
    }
}