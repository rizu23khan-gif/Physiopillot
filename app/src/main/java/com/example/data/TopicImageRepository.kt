package com.example.data

import android.content.Context
import android.util.Log
import java.io.FileNotFoundException
import java.io.IOException

object TopicImageRepository {
    private const val TAG = "TopicImageRepository"

    // Maps topic ID to their image gallery configuration
    private val staticGalleries = mapOf<String, List<ImageAsset>>(
        // Deltoid Anatomy (Pilot Concept)
        "a_0" to listOf(
            ImageAsset(
                id = "img_deltoid_anatomy",
                title = "Deltoid Muscle Anatomy",
                assetPath = "images/anatomy/deltoid_anatomy.webp",
                caption = "Anatomic illustration highlighting anterior, middle, and posterior heads of the Deltoid along with their respective origins (clavicle, acromion, spine of scapula)."
            )
        ),
        // Biceps Brachii
        "a_1" to listOf(
            ImageAsset(
                id = "img_biceps_anatomy_main",
                title = "Biceps Brachii Anatomy",
                assetPath = "images/anatomy/biceps_anatomy.webp",
                caption = "Detailed view showing the long head and short head of Biceps Brachii, highlighting their proximal origins and distal radial insertion."
            ),
            ImageAsset(
                id = "img_biceps_tendon",
                title = "Biceps Origins and Insertion Details",
                assetPath = "images/anatomy/biceps_insertion.png",
                caption = "Detailed anatomical perspective of the radial tuberosity insertion and coracoid attachment."
            )
        ),
        // Triceps Brachii
        "a_2" to listOf(
            ImageAsset(
                id = "img_triceps_anatomy",
                title = "Triceps Brachii Anatomy",
                assetPath = "images/anatomy/triceps_anatomy.webp",
                caption = "Anatomy of the posterior compartment of the arm detailing the Long, Lateral, and Medial heads of the Triceps Brachii inserting into the Olecranon process of the Ulna."
            )
        ),
        // Coracobrachialis
        "a_4" to listOf(
            ImageAsset(
                id = "img_coracobrachialis_anatomy",
                title = "Coracobrachialis Anatomy",
                assetPath = "images/anatomy/coracobrachialis_anatomy.webp",
                caption = "Anatomical view displaying the deep anterior arm muscle, rising from the coracoid process of the scapula and inserting into the medial humerus shaft."
            )
        ),
        // Supraspinatus
        "a_5" to listOf(
            ImageAsset(
                id = "img_supraspinatus_anatomy",
                title = "Supraspinatus Muscle",
                assetPath = "images/joints/supraspinatus_anatomy.webp",
                caption = "Illustration of the posterior scapular region showing the Supraspinatus muscle passing under the acromion to insert onto the greater tubercle of the humerus."
            )
        ),
        // Infraspinatus
        "a_6" to listOf(
            ImageAsset(
                id = "img_infraspinatus_anatomy",
                title = "Infraspinatus Muscle",
                assetPath = "images/joints/infraspinatus_anatomy.webp",
                caption = "Anatomy of the posterior face of the scapula, displaying the Infraspinatus muscle covering the infraspinous fossa and acting as a primary external rotator."
            )
        ),
        // Teres Minor
        "a_7" to listOf(
            ImageAsset(
                id = "img_teres_minor_anatomy",
                title = "Teres Minor Muscle",
                assetPath = "images/joints/teres_minor_anatomy.webp",
                caption = "Anatomical illustration showing the Teres Minor muscle running from the lateral border of the scapula to the lower facet of the greater tubercle of the humerus."
            )
        ),
        // Teres Major
        "a_8" to listOf(
            ImageAsset(
                id = "img_teres_major_anatomy",
                title = "Teres Major Muscle",
                assetPath = "images/joints/teres_major_anatomy.jpg",
                caption = "Posterior view highlighting the Teres Major running from the inferior angle of the scapula to insert on the medial lip of the intertubercular sulcus (groove) of the humerus."
            )
        ),
        // Subscapularis
        "a_9" to listOf(
            ImageAsset(
                id = "img_subscapularis_anatomy",
                title = "Subscapularis Muscle",
                assetPath = "images/joints/subscapularis_anatomy.webp",
                caption = "Anterior view of the scapula displaying the large Subscapularis muscle filling the subscapular fossa and inserting into the lesser tubercle of the humerus."
            )
        ),
        // Pectoralis Major
        "a_11" to listOf(
            ImageAsset(
                id = "img_pectoralis_major_anatomy",
                title = "Pectoralis Major Muscle",
                assetPath = "images/anatomy/pectoralis_major_anatomy.webp",
                caption = "Anterior wall anatomy displaying the lateral and medial heads of the Pectoralis Major, focusing on its clavicular and sternocostal heads."
            )
        ),
        // Pectoralis Minor
        "a_12" to listOf(
            ImageAsset(
                id = "img_pectoralis_minor_anatomy",
                title = "Pectoralis Minor Muscle",
                assetPath = "images/anatomy/pectoralis_minor_anatomy.webp",
                caption = "Deep anterior chest wall visualization detailing the origin of Pectoralis Minor from ribs 3-5 and insertion to the coracoid process."
            )
        ),
        // Latissimus Dorsi
        "a_13" to listOf(
            ImageAsset(
                id = "img_latissimus_dorsi_anatomy",
                title = "Latissimus Dorsi Muscle",
                assetPath = "images/anatomy/latissimus_dorsi_anatomy.webp",
                caption = "Anatomy of the superficial back showing the massive triangular Latissimus Dorsi muscle wrapping up into the intertubercular groove of the humerus."
            )
        ),
        // Serratus Anterior
        "a_14" to listOf(
            ImageAsset(
                id = "img_serratus_anterior_anatomy",
                title = "Serratus Anterior Muscle",
                assetPath = "images/anatomy/serratus_anterior_anatomy.webp",
                caption = "Anatomy of the lateral ribcage detailing the Serratus Anterior origins and its insertion along the entire medial border of the scapula."
            )
        ),
        // Trapezius
        "a_15" to listOf(
            ImageAsset(
                id = "img_trapezius_anatomy",
                title = "Trapezius Muscle",
                assetPath = "images/anatomy/trapezius_anatomy.webp",
                caption = "Posterior neck and upper back anatomy detailing superior, middle, and inferior fibers of the Trapezius spanning from occiput to lower thoracic vertebrae."
            )
        ),
        // Rhomboids
        "a_16" to listOf(
            ImageAsset(
                id = "img_rhomboids_anatomy",
                title = "Rhomboid Major & Minor Muscles",
                assetPath = "images/anatomy/rhomboids_anatomy.webp",
                caption = "Posterior shoulder girdle muscles displaying Rhomboids running from the ligamentum nuchae and spinous processes of C7-T5 to the medial scapular border."
            )
        ),
        // Standard mapping examples of topic IDs mapping to their exact category subfolders
        "mn_biceps" to listOf(
            ImageAsset(
                id = "img_biceps_anatomy",
                title = "Biceps Brachii Anatomy",
                assetPath = "images/anatomy/biceps_anatomy.webp",
                caption = "Anatomic illustration showing long and short heads of Biceps Brachii."
            ),
            ImageAsset(
                id = "img_biceps_inserts",
                title = "Biceps Origins and Insertion",
                assetPath = "images/anatomy/biceps_insertion.png",
                caption = "Detailed view of the radial tuberosity insertion of the biceps."
            )
        ),
        "st_lachman" to listOf(
            ImageAsset(
                id = "img_lachman_test",
                title = "Lachman's Test Procedure",
                assetPath = "images/special_tests/lachman_procedure.jpg",
                caption = "Therapist hands stabilizing femur and translating tibia anteriorly."
            )
        ),
        "cond_stroke" to listOf(
            ImageAsset(
                id = "img_stroke_mca_pathway",
                title = "Middle Cerebral Artery Stroke Pathway",
                assetPath = "images/stroke/mca_pathway.png",
                caption = "Cerebral pathway depicting sensory/motor deficit localization."
            )
        )
    )

    /**
     * Resolves the TopicImageGallery for a given topic ID.
     * Only returns images that are physically present in the local assets.
     */
    fun getGalleryForTopic(context: Context, topicId: String): TopicImageGallery {
        val mappedImages = staticGalleries[topicId] ?: emptyList()
        val verifiedImages = mappedImages.filter { img ->
            isAssetAvailable(context, img.assetPath)
        }
        return TopicImageGallery(topicId = topicId, images = verifiedImages)
    }

    /**
     * Checks all static visual collections (Anatomy, Muscles, Nerves, Joints, Special Tests, Clinical Conditions, Gait Analysis, Stroke Pathways)
     * and returns available galleries.
     */
    fun getGalleriesForCategory(context: Context, categoryName: String): List<TopicImageGallery> {
        return staticGalleries.entries.map { entry ->
            val verified = entry.value.filter { img ->
                // Matches the folder name / category
                img.assetPath.contains("images/${categoryName.lowercase().replace(" ", "_")}/") &&
                        isAssetAvailable(context, img.assetPath)
            }
            TopicImageGallery(entry.key, verified)
        }.filter { it.images.isNotEmpty() }
    }

    /**
     * Safe checker to verify if a file exists in the standard Android assets folder.
     */
    fun isAssetAvailable(context: Context, path: String): Boolean {
        return try {
            context.assets.open(path).use { 
                // Successfully opened, so it exists
                true
            }
        } catch (e: FileNotFoundException) {
            false
        } catch (e: IOException) {
            false
        } catch (e: Exception) {
            Log.e(TAG, "Error checking asset existence for path: $path", e)
            false
        }
    }
}
