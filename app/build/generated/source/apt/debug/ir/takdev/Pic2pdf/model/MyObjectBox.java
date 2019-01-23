package ir.takdev.Pic2pdf.model;

import io.objectbox.BoxStore;
import io.objectbox.BoxStoreBuilder;
import io.objectbox.ModelBuilder;
import io.objectbox.ModelBuilder.EntityBuilder;
import io.objectbox.model.PropertyFlags;
import io.objectbox.model.PropertyType;


// THIS CODE IS GENERATED BY ObjectBox, DO NOT EDIT.
/**
 * Starting point for working with your ObjectBox. All boxes are set up for your objects here.
 * <p>
 * First steps (Android): get a builder using {@link #builder()}, call {@link BoxStoreBuilder#androidContext(Object)},
 * and {@link BoxStoreBuilder#build()} to get a {@link BoxStore} to work with.
 */
public class MyObjectBox {

    public static BoxStoreBuilder builder() {
        BoxStoreBuilder builder = new BoxStoreBuilder(getModel());
        builder.entity(Img_.__INSTANCE);
        builder.entity(PDF_.__INSTANCE);
        return builder;
    }

    private static byte[] getModel() {
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.lastEntityId(2, 6759200314868864681L);
        modelBuilder.lastIndexId(0, 0L);
        modelBuilder.lastRelationId(0, 0L);

        EntityBuilder entityBuilder;

        entityBuilder = modelBuilder.entity("Img");
        entityBuilder.id(2, 6759200314868864681L).lastPropertyId(3, 7083997056174278419L);
        entityBuilder.property("id", PropertyType.Long).id(1, 3670767641485821249L)
            .flags(PropertyFlags.ID | PropertyFlags.NOT_NULL);
        entityBuilder.property("name", PropertyType.String).id(2, 4467272119534398217L);
        entityBuilder.property("date", PropertyType.Date).id(3, 7083997056174278419L);
        entityBuilder.entityDone();

        entityBuilder = modelBuilder.entity("PDF");
        entityBuilder.id(1, 7680849925276873124L).lastPropertyId(3, 8441389059548293313L);
        entityBuilder.property("id", PropertyType.Long).id(1, 1368455533620575576L)
            .flags(PropertyFlags.ID | PropertyFlags.NOT_NULL);
        entityBuilder.property("size", PropertyType.String).id(2, 3962409232988212101L);
        entityBuilder.property("name", PropertyType.String).id(3, 8441389059548293313L);
        entityBuilder.entityDone();

        return modelBuilder.build();
    }

}