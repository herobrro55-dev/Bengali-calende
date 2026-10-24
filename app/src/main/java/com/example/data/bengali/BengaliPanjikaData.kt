package com.example.data.bengali

import com.example.data.model.AuspiciousCategory
import com.example.data.model.AuspiciousDate
import com.example.data.model.BengaliMonth
import com.example.data.model.Festival
import com.example.data.model.FestivalType
import com.example.data.model.MoonPhaseEvent
import com.example.data.model.MoonPhaseType
import java.time.LocalDate

object BengaliPanjikaData {

    val MOON_PHASE_EVENTS: List<MoonPhaseEvent> = listOf(
        // 2026 Purnima & Amavasya Schedule
        MoonPhaseEvent(
            id = "purnima_2026_01",
            date = LocalDate.of(2026, 1, 3),
            type = MoonPhaseType.PURNIMA,
            bengaliMonth = BengaliMonth.POUSH,
            bengaliDay = 19,
            bengaliYear = 1432,
            bengaliName = "পৌষ পূর্ণিমা",
            englishName = "Poush Purnima",
            startTime = "সকাল ০৮:১৫",
            endTime = "পরদিন সকাল ০৭:১০",
            significanceBengali = "পৌষ মাসের পবিত্র পূর্ণিমা স্নান ও সত্যনারায়ণ ব্রত পালনের পুণ্য তিথি।",
            significanceEnglish = "Auspicious bath and Satyanarayan brata during the sacred month of Poush.",
            ritualsBengali = "প্রভাত স্নান, তুলসী তলায় প্রদীপ প্রজ্জ্বলন ও নিরামিষ ভোগ নিবেদন।",
            ritualsEnglish = "Morning holy bath, lighting lamps at Tulsi altar, and offering prasad."
        ),
        MoonPhaseEvent(
            id = "amavasya_2026_01",
            date = LocalDate.of(2026, 1, 18),
            type = MoonPhaseType.AMAVASYA,
            bengaliMonth = BengaliMonth.MAGH,
            bengaliDay = 4,
            bengaliYear = 1432,
            bengaliName = "মৌনী অমাবস্যা (মাঘ অমাবস্যা)",
            englishName = "Mauni Amavasya",
            startTime = "সকাল ১০:২০",
            endTime = "পরদিন সকাল ০৯:৪০",
            significanceBengali = "মহাতীর্থ প্রয়াগ ও গঙ্গাসাগর স্নানের পরম পুণ্যময় মৌনী অমাবস্যা তিথি।",
            significanceEnglish = "Highly revered day for silent contemplation and sacred river ablutions.",
            ritualsBengali = "মৌনব্রত পালন, পিতৃপুরুষের উদ্দেশ্যে তর্পণ ও দরিদ্র নারায়ণ সেবা।",
            ritualsEnglish = "Observing silence, ancestral Tarpan, and charity to the needy."
        ),
        MoonPhaseEvent(
            id = "purnima_2026_02",
            date = LocalDate.of(2026, 2, 1),
            type = MoonPhaseType.PURNIMA,
            bengaliMonth = BengaliMonth.MAGH,
            bengaliDay = 18,
            bengaliYear = 1432,
            bengaliName = "মাঘী পূর্ণিমা",
            englishName = "Maghi Purnima",
            startTime = "দুপুর ১২:৫০",
            endTime = "পরদিন সকাল ১১:৩০",
            significanceBengali = "মাঘ স্নানের সমাপ্তি এবং কুলগুরু ও শ্রীকৃষ্ণের পূজার পবিত্র দিন।",
            significanceEnglish = "Culmination of Magh month austerities and Lord Krishna worship.",
            ritualsBengali = "গঙ্গাস্নান, অন্নদান, সত্যনারায়ণ পূজা ও গীতাপাঠ।",
            ritualsEnglish = "Holy river bath, Anna daan (food donation), and Gita recitation."
        ),
        MoonPhaseEvent(
            id = "amavasya_2026_02",
            date = LocalDate.of(2026, 2, 17),
            type = MoonPhaseType.AMAVASYA,
            bengaliMonth = BengaliMonth.FALGUN,
            bengaliDay = 5,
            bengaliYear = 1432,
            bengaliName = "ফাল্গুনী অমাবস্যা",
            englishName = "Falgun Amavasya",
            startTime = "সন্ধ্যা ০৬:১০",
            endTime = "পরদিন বিকাল ০৫:২০",
            significanceBengali = "শিবরাত্রির প্রাক্কালে বিশেষ অমাবস্যা তিথি ও পূর্বপুরুষের শান্তির জন্য তর্পণ।",
            significanceEnglish = "Pre-Shivratri new moon, ideal for prayers and ancestral remembrance.",
            ritualsBengali = "সন্ধ্যায় শিব মন্দিরে দীপদান, তিল তর্পণ।",
            ritualsEnglish = "Lighting lamps at Shiva temple, Til Tarpan rituals."
        ),
        MoonPhaseEvent(
            id = "purnima_2026_03",
            date = LocalDate.of(2026, 3, 3),
            type = MoonPhaseType.PURNIMA,
            bengaliMonth = BengaliMonth.FALGUN,
            bengaliDay = 19,
            bengaliYear = 1432,
            bengaliName = "দোল পূর্ণিমা ও গৌর পূর্ণিমা",
            englishName = "Dol Purnima & Gaura Purnima",
            startTime = "বিকাল ০৩:৩০",
            endTime = "পরদিন দুপুর ০২:১৫",
            significanceBengali = "শ্রী চৈতন্য মহাপ্রভুর আবির্ভাব তিথি এবং বসন্তোৎসব ও দোলযাত্রা উৎসব।",
            significanceEnglish = "Appearance day of Sri Chaitanya Mahaprabhu and joyful Dol Jatra / Holi festival.",
            ritualsBengali = "শ্রী রাধাকৃষ্ণের চরণে আবীর অর্পণ, হরিনাম সংকীর্তন ও মালপোয়া ভোগ।",
            ritualsEnglish = "Offering abir (color) to Radha-Krishna, Harinam Sankirtan, and sweets."
        ),
        MoonPhaseEvent(
            id = "amavasya_2026_03",
            date = LocalDate.of(2026, 3, 19),
            type = MoonPhaseType.AMAVASYA,
            bengaliMonth = BengaliMonth.CHOITRO,
            bengaliDay = 5,
            bengaliYear = 1432,
            bengaliName = "চৈত্র অমাবস্যা",
            englishName = "Chaitra Amavasya",
            startTime = "সকাল ০৭:১৫",
            endTime = "পরদিন সকাল ০৬:৪৫",
            significanceBengali = "বছরের অন্তিম মাসের অমাবস্যা তিথি, অশুভ শক্তি নাশে কালী আরাধনা।",
            significanceEnglish = "Last new moon of Bengali year, propitiating Goddess Kali.",
            ritualsBengali = "মা কালীর পূজা, নিশাকালে হোম ও আরতি।",
            ritualsEnglish = "Kali puja prayers, evening Aarti and offerings."
        ),
        MoonPhaseEvent(
            id = "purnima_2026_04",
            date = LocalDate.of(2026, 4, 2),
            type = MoonPhaseType.PURNIMA,
            bengaliMonth = BengaliMonth.CHOITRO,
            bengaliDay = 19,
            bengaliYear = 1432,
            bengaliName = "চৈত্র পূর্ণিমা (হনুমান জয়ন্তী)",
            englishName = "Chaitra Purnima (Hanuman Jayanti)",
            startTime = "ভোর ০৫:৩০",
            endTime = "পরদিন ভোর ০৪:২৫",
            significanceBengali = "বীর হনুমানজীর আবির্ভাব তিথি এবং সত্যনারায়ণ ব্রত।",
            significanceEnglish = "Birth anniversary of Lord Hanuman and sacred fasting.",
            ritualsBengali = "হনুমান চালিসা পাঠ, মেটে সিঁদুরের প্রলেপ ও লাড্ডু ভোগ।",
            ritualsEnglish = "Hanuman Chalisa recitation and sweet offerings."
        ),
        MoonPhaseEvent(
            id = "amavasya_2026_04",
            date = LocalDate.of(2026, 4, 17),
            type = MoonPhaseType.AMAVASYA,
            bengaliMonth = BengaliMonth.BOISHAKH,
            bengaliDay = 4,
            bengaliYear = 1433,
            bengaliName = "বৈশাখী অমাবস্যা",
            englishName = "Boishakh Amavasya",
            startTime = "সন্ধ্যা ০৮:৪৫",
            endTime = "পরদিন সন্ধ্যা ০৭:৩০",
            significanceBengali = "নতুন বছরের প্রথম অমাবস্যা, শনিদেবের বিশেষ পূজা ও তর্পণ।",
            significanceEnglish = "First new moon of the new Bengali Year 1433.",
            ritualsBengali = "অশ্বত্থ গাছে জলদান ও সর্ষের তেলের প্রদীপ প্রজ্জ্বলন।",
            ritualsEnglish = "Watering sacred peepal tree and mustard oil lamp offering."
        ),
        MoonPhaseEvent(
            id = "purnima_2026_05",
            date = LocalDate.of(2026, 5, 1),
            type = MoonPhaseType.PURNIMA,
            bengaliMonth = BengaliMonth.BOISHAKH,
            bengaliDay = 18,
            bengaliYear = 1433,
            bengaliName = "বুদ্ধ পূর্ণিমা ও বৈশাখী পূর্ণিমা",
            englishName = "Buddha Purnima",
            startTime = "দুপুর ০১:২০",
            endTime = "পরদিন সকাল ১১:৫০",
            significanceBengali = "গৌতম বুদ্ধের জন্ম, বোধিলাভ ও মহাপরিনির্বাণ তিথি।",
            significanceEnglish = "Triple blessed day marking the birth, enlightenment, and nirvana of Buddha.",
            ritualsBengali = "শান্তি প্রার্থনা, বুদ্ধ বন্দনা, শ্বেতপুষ্প অর্পণ ও অহিংসা ব্রত।",
            ritualsEnglish = "Peace meditation, offering white flowers, and acts of compassion."
        ),
        MoonPhaseEvent(
            id = "amavasya_2026_05",
            date = LocalDate.of(2026, 5, 16),
            type = MoonPhaseType.AMAVASYA,
            bengaliMonth = BengaliMonth.JOISHTHO,
            bengaliDay = 3,
            bengaliYear = 1433,
            bengaliName = "ফলহারিণী কালিকা অমাবস্যা (বট সাবিত্রী)",
            englishName = "Phalaharini Kali Amavasya",
            startTime = "সকাল ০৯:১০",
            endTime = "পরদিন সকাল ০৮:০০",
            significanceBengali = "শ্রীরামকৃষ্ণের শ্রীমা সারদাদেবীকে ষোড়শী রূপে পূজার পরম পুণ্য তিথি।",
            significanceEnglish = "Goddess Kali is worshipped with summer fruits; Shodashi Puja day.",
            ritualsBengali = "নানা প্রকার ঋতুভিত্তিক ফল নিবেদন, নিশাপূজা ও জপধ্যান।",
            ritualsEnglish = "Offering seasonal fruits to Mother Kali, nocturnal chanting."
        ),
        MoonPhaseEvent(
            id = "purnima_2026_05_31",
            date = LocalDate.of(2026, 5, 31),
            type = MoonPhaseType.PURNIMA,
            bengaliMonth = BengaliMonth.JOISHTHO,
            bengaliDay = 18,
            bengaliYear = 1433,
            bengaliName = "দেবস্নান পূর্ণিমা",
            englishName = "Snan Yatra Purnima",
            startTime = "রাত ০৯:০৫",
            endTime = "পরদিন সন্ধ্যা ০৭:২০",
            significanceBengali = "ভগবান জগন্নাথ, বলভদ্র ও সুভদ্রার মহাজল স্নানযাত্রা তিথি।",
            significanceEnglish = "Grand ceremonial bathing of Lord Jagannath before Rath Yatra.",
            ritualsBengali = "সুগন্ধি ১০৮ কলসি জলে জগন্নাথদেবের অভিষেক দর্শন।",
            ritualsEnglish = "Ceremonial 108 pot water bath of deities."
        ),
        MoonPhaseEvent(
            id = "amavasya_2026_06",
            date = LocalDate.of(2026, 6, 14),
            type = MoonPhaseType.AMAVASYA,
            bengaliMonth = BengaliMonth.ASHARH,
            bengaliDay = 1,
            bengaliYear = 1433,
            bengaliName = "আষাঢ়ী অমাবস্যা (অম্বুবাচী সমাগত)",
            englishName = "Asharh Amavasya",
            startTime = "দুপুর ১২:৪০",
            endTime = "পরদিন সকাল ১১:১৫",
            significanceBengali = "মা কামাখ্যার অম্বুবাচী মহাযোগের প্রাক্কালে পুণ্য অমাবস্যা।",
            significanceEnglish = "New moon heralding sacred Ambubachi festivities.",
            ritualsBengali = "দেবী কালীর বন্দনা ও নিরামিষ আহার গ্রহণ।",
            ritualsEnglish = "Devotional prayers to Divine Mother and fasting."
        ),
        MoonPhaseEvent(
            id = "purnima_2026_06",
            date = LocalDate.of(2026, 6, 29),
            type = MoonPhaseType.PURNIMA,
            bengaliMonth = BengaliMonth.ASHARH,
            bengaliDay = 16,
            bengaliYear = 1433,
            bengaliName = "গুরু পূর্ণিমা (ব্যাস পূর্ণিমা)",
            englishName = "Guru Purnima",
            startTime = "ভোর ০৫:৫০",
            endTime = "পরদিন ভোর ০৪:৩০",
            significanceBengali = "মহর্ষি বেদব্যাসের জন্মজয়ন্তী এবং গুরুদেবের চরণে প্রণতি ও কৃতজ্ঞতা জ্ঞাপন।",
            significanceEnglish = "Honoring spiritual masters and mentors on Vyasa Purnima.",
            ritualsBengali = "গুরুপূজা, পাদোদক গ্রহণ, গীতা পাঠ ও গুরুপ্রণাম।",
            ritualsEnglish = "Guru vandana, seeking blessings, offering floral garland."
        ),
        MoonPhaseEvent(
            id = "amavasya_2026_07",
            date = LocalDate.of(2026, 7, 14),
            type = MoonPhaseType.AMAVASYA,
            bengaliMonth = BengaliMonth.SRABON,
            bengaliDay = 1,
            bengaliYear = 1433,
            bengaliName = "শ্রাবণী হরিয়ালি অমাবস্যা",
            englishName = "Hariyali Amavasya",
            startTime = "রাত ০৩:৩০",
            endTime = "পরদিন রাত ০২:০০",
            significanceBengali = "শ্রাবণ মাসের বৃক্ষরোপণ, প্রকৃতি বন্দনা ও শিবপূজার পুণ্য তিথি।",
            significanceEnglish = "Sacred Shravan new moon for tree planting and Shiva devotion.",
            ritualsBengali = "শিবলিঙ্গে বিল্বপত্র ও গঙ্গাজল অভিষেক, বৃক্ষরোপণ।",
            ritualsEnglish = "Offering bael leaves and Ganga water to Shivling."
        ),
        MoonPhaseEvent(
            id = "purnima_2026_07",
            date = LocalDate.of(2026, 7, 28),
            type = MoonPhaseType.PURNIMA,
            bengaliMonth = BengaliMonth.SRABON,
            bengaliDay = 15,
            bengaliYear = 1433,
            bengaliName = "ঝুলন পূর্ণিমা ও রাখি পূর্ণিমা",
            englishName = "Jhulan Purnima & Rakhi",
            startTime = "দুপুর ০২:১৫",
            endTime = "পরদিন দুপুর ০১:০০",
            significanceBengali = "শ্রীকৃষ্ণের ঝুলনযাত্রার সমাপন এবং ভ্রাতৃত্বের রাখি বন্ধন উৎসব।",
            significanceEnglish = "Raksha Bandhan and concluding day of Krishna's Jhulan Yatra.",
            ritualsBengali = "ভাইয়ের হাতে রাখি বন্ধন, মিষ্টান্ন প্রদান ও ঝুলনোৎসব আরতি।",
            ritualsEnglish = "Tying Rakhi on siblings' wrists, sweets sharing, Jhulan arti."
        ),
        MoonPhaseEvent(
            id = "amavasya_2026_08",
            date = LocalDate.of(2026, 8, 12),
            type = MoonPhaseType.AMAVASYA,
            bengaliMonth = BengaliMonth.BHADRO,
            bengaliDay = 1,
            bengaliYear = 1433,
            bengaliName = "ভাদ্র অমাবস্যা (কুশোৎপাটনী)",
            englishName = "Bhadra Amavasya",
            startTime = "সন্ধ্যা ০৬:৪৫",
            endTime = "পরদিন বিকাল ০৫:১৫",
            significanceBengali = "কুশ সংগ্রহ ও পিতৃপুরুষের আত্মার শান্তির উদ্দেশ্যে তর্পণ তিথি।",
            significanceEnglish = "Sacred Kush grass collection and ancestral remembrance.",
            ritualsBengali = "গঙ্গা ঘাটে তর্পণ ও তিলজল দান।",
            ritualsEnglish = "Tarpan rituals at holy ghats."
        ),
        MoonPhaseEvent(
            id = "purnima_2026_08",
            date = LocalDate.of(2026, 8, 27),
            type = MoonPhaseType.PURNIMA,
            bengaliMonth = BengaliMonth.BHADRO,
            bengaliDay = 16,
            bengaliYear = 1433,
            bengaliName = "ভাদ্রী পূর্ণিমা ও সত্যনারায়ণ ব্রত",
            englishName = "Bhadra Purnima",
            startTime = "রাত ১১:২০",
            endTime = "পরদিন রাত ১০:০৫",
            significanceBengali = "ভাগবত পুরাণ শ্রবণ ও সত্যনারায়ণ দেবের বিশেষ পূজা তিথি।",
            significanceEnglish = "Auspicious for listening to Srimad Bhagavatam and Satyanarayan Puja.",
            ritualsBengali = "সন্ধ্যায় সিন্নি ভোগ প্রদান ও সত্যনারায়ণ পাঁচালী পাঠ।",
            ritualsEnglish = "Offering sinni prasad and reciting Satyanarayan panchali."
        ),
        MoonPhaseEvent(
            id = "amavasya_2026_09",
            date = LocalDate.of(2026, 9, 10),
            type = MoonPhaseType.AMAVASYA,
            bengaliMonth = BengaliMonth.BHADRO,
            bengaliDay = 30,
            bengaliYear = 1433,
            bengaliName = "মহালয়া অমাবস্যা (পিতৃ অমাবস্যা)",
            englishName = "Mahalaya Amavasya",
            startTime = "সকাল ১০:৫০",
            endTime = "পরদিন সকাল ০৯:১৫",
            significanceBengali = "পিতৃপক্ষের অবসান, মহিষাসুরমর্দিনী দেবী দুর্গার মর্ত্যে আগমন বার্তা ও তর্পণ।",
            significanceEnglish = "End of Pitru Paksha, invocation of Goddess Durga and holy Tarpan.",
            ritualsBengali = "ভোরে বীরেন্দ্রকৃষ্ণ ভদ্রের চণ্ডীপাঠ শ্রবণ, গঙ্গায় তর্পণ ও দরিদ্র সেবা।",
            ritualsEnglish = "Listening to Mahisasuramardini dawn chant, sacred river tarpan."
        ),
        MoonPhaseEvent(
            id = "purnima_2026_09",
            date = LocalDate.of(2026, 9, 25),
            type = MoonPhaseType.PURNIMA,
            bengaliMonth = BengaliMonth.ASHWIN,
            bengaliDay = 15,
            bengaliYear = 1433,
            bengaliName = "কোজাগরী লক্ষ্মী পূর্ণিমা",
            englishName = "Kojagari Lakshmi Purnima",
            startTime = "বিকাল ০৪:৪০",
            endTime = "পরদিন বিকাল ০৩:১০",
            significanceBengali = "বাঙালির পরম আরাধ্য মা লক্ষ্মীর বার্ষিক আবাহন ও গৃহস্থের সৌভাগ্য প্রার্থনা।",
            significanceEnglish = "The grand Bengali Kojagari Lakshmi Puja honoring the Goddess of Prosperity.",
            ritualsBengali = "চাল ও আলপনা অঙ্কন, নারকেল নাড়ু, খিচুড়ি ভোগ ও নিশাকালে জাগরণ।",
            ritualsEnglish = "Drawing alpona, preparing coconut laddoos, night vigil for Lakshmi."
        ),
        MoonPhaseEvent(
            id = "amavasya_2026_10",
            date = LocalDate.of(2026, 10, 10),
            type = MoonPhaseType.AMAVASYA,
            bengaliMonth = BengaliMonth.ASHWIN,
            bengaliDay = 30,
            bengaliYear = 1433,
            bengaliName = "দীপান্বিতা শ্যামা কালীপূজা অমাবস্যা",
            englishName = "Dipannita Kali Puja Amavasya",
            startTime = "রাত ০১:১৫",
            endTime = "পরদিন রাত ১১:৫০",
            significanceBengali = "অন্ধকার দূর করে আলোর দীপাবলি উৎসব ও মা শ্যামা কালীর মহা আরাধনা।",
            significanceEnglish = "Diwali festival of lights and nocturnal worship of Goddess Kali.",
            ritualsBengali = "চৌদ্দ প্রদীপ প্রজ্জ্বলন, নিশাকালে কালীপূজা ও শ্যামাসংগীত।",
            ritualsEnglish = "Lighting 14 lamps, night-long Kali puja, and fireworks."
        ),
        MoonPhaseEvent(
            id = "purnima_2026_10",
            date = LocalDate.of(2026, 10, 25),
            type = MoonPhaseType.PURNIMA,
            bengaliMonth = BengaliMonth.KARTIK,
            bengaliDay = 15,
            bengaliYear = 1433,
            bengaliName = "কার্তিক পূর্ণিমা ও রাস পূর্ণিমা",
            englishName = "Kartik Purnima & Raas Purnima",
            startTime = "সকাল ০৭:৫৫",
            endTime = "পরদিন সকাল ০৬:২০",
            significanceBengali = "শান্তিপুর ও নবদ্বীপে রাসোৎসব, দেব দীপাবলি ও পুণ্য স্নান।",
            significanceEnglish = "Grand Raas Leela festival of Sri Radha Krishna, Dev Deepavali.",
            ritualsBengali = "রাসলীলা দর্শন, নদীতে প্রদীপ ভাসানো ও কীর্তন শ্রবণ।",
            ritualsEnglish = "Floating clay lamps in rivers, enjoying kirtan and Raas dances."
        ),
        MoonPhaseEvent(
            id = "amavasya_2026_11",
            date = LocalDate.of(2026, 11, 9),
            type = MoonPhaseType.AMAVASYA,
            bengaliMonth = BengaliMonth.KARTIK,
            bengaliDay = 30,
            bengaliYear = 1433,
            bengaliName = "কার্তিক অমাবস্যা",
            englishName = "Kartik Amavasya",
            startTime = "বিকাল ০৩:৩০",
            endTime = "পরদিন দুপুর ০১:৪০",
            significanceBengali = "কার্তিক মাসের শেষ দিনে পিতৃ স্মরণ ও শনি শান্তি পূজা।",
            significanceEnglish = "End of Kartik month, peaceful remembrance and prayers.",
            ritualsBengali = "সন্ধ্যায় দীপদান ও বিষ্ণু সহস্রনাম পাঠ।",
            ritualsEnglish = "Evening lamp offering and prayer recitations."
        ),
        MoonPhaseEvent(
            id = "purnima_2026_11",
            date = LocalDate.of(2026, 11, 23),
            type = MoonPhaseType.PURNIMA,
            bengaliMonth = BengaliMonth.AGRAHAYAN,
            bengaliDay = 14,
            bengaliYear = 1433,
            bengaliName = "মার্গশীর্ষ পূর্ণিমা ও নবান্ন উৎসব",
            englishName = "Agrahayan Purnima (Nabanna)",
            startTime = "সন্ধ্যা ০৮:২০",
            endTime = "পরদিন সন্ধ্যা ০৬:৪৫",
            significanceBengali = "নতুন ধানের সুবাসে নবান্ন ব্রত ও মা অন্নপূর্ণার আরাধনা।",
            significanceEnglish = "Harvest festival of Nabanna with fresh crop offerings.",
            ritualsBengali = "নতুন চালের পায়েস ও পিঠে তৈরি করে কুলদেবতাকে নিবেদন।",
            ritualsEnglish = "Cooking harvest rice pudding (payesh) and pithe."
        ),
        MoonPhaseEvent(
            id = "amavasya_2026_12",
            date = LocalDate.of(2026, 12, 8),
            type = MoonPhaseType.AMAVASYA,
            bengaliMonth = BengaliMonth.AGRAHAYAN,
            bengaliDay = 29,
            bengaliYear = 1433,
            bengaliName = "অগ্রহায়ণ অমাবস্যা",
            englishName = "Agrahayan Amavasya",
            startTime = "রাত ০৫:২০",
            endTime = "পরদিন ভোর ০৩:৫০",
            significanceBengali = "শীতের সূচনায় পিতৃপুরুষের তৃপ্তির জন্য পিণ্ডদান ও তর্পণ।",
            significanceEnglish = "Winter new moon offering peace to ancestors.",
            ritualsBengali = "অন্নদান ও তিল তর্পণ।",
            ritualsEnglish = "Food donation and sacred prayers."
        ),
        MoonPhaseEvent(
            id = "purnima_2026_12",
            date = LocalDate.of(2026, 12, 23),
            type = MoonPhaseType.PURNIMA,
            bengaliMonth = BengaliMonth.POUSH,
            bengaliDay = 15,
            bengaliYear = 1433,
            bengaliName = "পৌষ পূর্ণিমা",
            englishName = "Poush Purnima",
            startTime = "সকাল ০৯:১০",
            endTime = "পরদিন সকাল ০৭:৩০",
            significanceBengali = "পবিত্র পৌষ মাসের পূর্ণিমা স্নান ও সত্যনারায়ণ ব্রত।",
            significanceEnglish = "Auspicious winter Purnima holy bath and worship.",
            ritualsBengali = "প্রভাত স্নান ও সত্যনারায়ণ কথা পাঠ।",
            ritualsEnglish = "Morning bath and Satyanarayan katha."
        )
    )

    val AUSPICIOUS_DATES: List<AuspiciousDate> = listOf(
        // Marriage Dates (বিবাহের শুভ লগ্ন)
        AuspiciousDate(
            id = "m_2026_01_22",
            date = LocalDate.of(2026, 1, 22),
            category = AuspiciousCategory.MARRIAGE,
            bengaliDateStr = "৮ মাঘ ১৪৩২",
            bengaliDateStrEn = "8 Magh 1432",
            tithi = "শুক্লা পঞ্চমী",
            nakshatra = "উত্তরভাদ্রপদ",
            auspiciousHoursBengali = "সন্ধ্যা ০৭:১৫ থেকে রাত ১১:২০ পর্যন্ত",
            auspiciousHoursEnglish = "7:15 PM to 11:20 PM",
            descriptionBengali = "অতি উত্তম বিবাহ লগ্ন। প্রজাপতি ও বৃহস্পতির শুভ দৃষ্টি বর্তমান।",
            descriptionEnglish = "Highly auspicious marriage muhurat with Jupiter's favorable aspect."
        ),
        AuspiciousDate(
            id = "m_2026_02_08",
            date = LocalDate.of(2026, 2, 8),
            category = AuspiciousCategory.MARRIAGE,
            bengaliDateStr = "২৫ মাঘ ১৪৩২",
            bengaliDateStrEn = "25 Magh 1432",
            tithi = "শুক্লা একাদশী",
            nakshatra = "রোহিণী",
            auspiciousHoursBengali = "রাত ০৮:০০ থেকে রাত ১২:৪৫ পর্যন্ত",
            auspiciousHoursEnglish = "8:00 PM to 12:45 AM",
            descriptionBengali = "শুভ রোহিণীনক্ষত্র যুক্ত গোধূলি ও নিশিতা বিবাহ লগ্ন।",
            descriptionEnglish = "Favorable Rohini constellation alignment for wedding rituals."
        ),
        AuspiciousDate(
            id = "m_2026_02_14",
            date = LocalDate.of(2026, 2, 14),
            category = AuspiciousCategory.MARRIAGE,
            bengaliDateStr = "২ ফাল্গুন ১৪৩২",
            bengaliDateStrEn = "2 Falgun 1432",
            tithi = "কৃষ্ণা ত্রয়োদশী",
            nakshatra = "উত্তরাষাঢ়া",
            auspiciousHoursBengali = "সন্ধ্যা ০৬:৪৫ থেকে রাত ১০:৩০ পর্যন্ত",
            auspiciousHoursEnglish = "6:45 PM to 10:30 PM",
            descriptionBengali = "সুখ-সমৃদ্ধির জন্য অত্যন্ত ফলদায়ী বিবাহ যোগ।",
            descriptionEnglish = "Blessed wedding muhurat for long-term prosperity."
        ),
        AuspiciousDate(
            id = "m_2026_04_21",
            date = LocalDate.of(2026, 4, 21),
            category = AuspiciousCategory.MARRIAGE,
            bengaliDateStr = "৮ বৈশাখ ১৪৩৩",
            bengaliDateStrEn = "8 Boishakh 1433",
            tithi = "শুক্লা পঞ্চমী",
            nakshatra = "মৃগশিরা",
            auspiciousHoursBengali = "সন্ধ্যা ০৭:৩০ থেকে রাত ০১:১০ পর্যন্ত",
            auspiciousHoursEnglish = "7:30 PM to 1:10 AM",
            descriptionBengali = "বৈশাখ মাসের প্রথম সারির শুভ বিবাহ লগ্ন।",
            descriptionEnglish = "Prime marriage muhurat of early Boishakh."
        ),
        AuspiciousDate(
            id = "m_2026_05_10",
            date = LocalDate.of(2026, 5, 10),
            category = AuspiciousCategory.MARRIAGE,
            bengaliDateStr = "২৭ বৈশাখ ১৪৩৩",
            bengaliDateStrEn = "27 Boishakh 1433",
            tithi = "কৃষ্ণা দশমী",
            nakshatra = "উত্তরফল্গুনী",
            auspiciousHoursBengali = "রাত ০৮:১৫ থেকে রাত ১২:০০ পর্যন্ত",
            auspiciousHoursEnglish = "8:15 PM to 12:00 AM",
            descriptionBengali = "সৌভাগ্য বৃদ্ধির উত্তম যোগ।",
            descriptionEnglish = "Auspicious wedding union period."
        ),
        AuspiciousDate(
            id = "m_2026_11_20",
            date = LocalDate.of(2026, 11, 20),
            category = AuspiciousCategory.MARRIAGE,
            bengaliDateStr = "১১ অগ্রহায়ণ ১৪৩৩",
            bengaliDateStrEn = "11 Agrahayan 1433",
            tithi = "শুক্লা একাদশী",
            nakshatra = "রেবতী",
            auspiciousHoursBengali = "সন্ধ্যা ০৬:২০ থেকে রাত ১১:০০ পর্যন্ত",
            auspiciousHoursEnglish = "6:20 PM to 11:00 PM",
            descriptionBengali = "অগ্রহায়ণ মাসের সর্বশ্রেষ্ঠ বিবাহ লগ্ন।",
            descriptionEnglish = "Top auspicious marriage timing of Agrahayan."
        ),
        AuspiciousDate(
            id = "m_2026_12_05",
            date = LocalDate.of(2026, 12, 5),
            category = AuspiciousCategory.MARRIAGE,
            bengaliDateStr = "২৬ অগ্রহায়ণ ১৪৩৩",
            bengaliDateStrEn = "26 Agrahayan 1433",
            tithi = "কৃষ্ণা একাদশী",
            nakshatra = "স্বাতী",
            auspiciousHoursBengali = "সন্ধ্যা ০৭:০০ থেকে রাত ১০:৪৫ পর্যন্ত",
            auspiciousHoursEnglish = "7:00 PM to 10:45 PM",
            descriptionBengali = "দাম্পত্য জীবনের শান্তি ও প্রীতির শুভ লগ্ন।",
            descriptionEnglish = "Promising harmony and marital joy."
        ),

        // Griha Pravesh (গৃহপ্রবেশ)
        AuspiciousDate(
            id = "gp_2026_01_26",
            date = LocalDate.of(2026, 1, 26),
            category = AuspiciousCategory.GRIHA_PRAVESH,
            bengaliDateStr = "১২ মাঘ ১৪৩২",
            bengaliDateStrEn = "12 Magh 1432",
            tithi = "শুক্লা অষ্টমী",
            nakshatra = "রোহিণী",
            auspiciousHoursBengali = "সকাল ০৭:১৫ থেকে সকাল ১০:২০ পর্যন্ত",
            auspiciousHoursEnglish = "7:15 AM to 10:20 AM",
            descriptionBengali = "নতুন ভবনে বাস্তু পূজা ও গৃহপ্রবেশের পরম শুভ মুহূর্ত।",
            descriptionEnglish = "Ideal time for Vastu Puja and moving into a new home."
        ),
        AuspiciousDate(
            id = "gp_2026_02_20",
            date = LocalDate.of(2026, 2, 20),
            category = AuspiciousCategory.GRIHA_PRAVESH,
            bengaliDateStr = "৮ ফাল্গুন ১৪৩২",
            bengaliDateStrEn = "8 Falgun 1432",
            tithi = "শুক্লা তৃতীয়া",
            nakshatra = "উত্তরাষাঢ়া",
            auspiciousHoursBengali = "সকাল ০৮:৩০ থেকে দুপুর ১১:৪৫ পর্যন্ত",
            auspiciousHoursEnglish = "8:30 AM to 11:45 AM",
            descriptionBengali = "অমৃত যোগ যুক্ত গৃহপ্রবেশ ক্ষণ। সংসারে শ্রী ও সমৃদ্ধি বৃদ্ধি।",
            descriptionEnglish = "Amrita Yoga aligned housewarming for abundance."
        ),
        AuspiciousDate(
            id = "gp_2026_05_05",
            date = LocalDate.of(2026, 5, 5),
            category = AuspiciousCategory.GRIHA_PRAVESH,
            bengaliDateStr = "২২ বৈশাখ ১৪৩৩",
            bengaliDateStrEn = "22 Boishakh 1433",
            tithi = "কৃষ্ণা পঞ্চমী",
            nakshatra = "পূর্বাষাঢ়া",
            auspiciousHoursBengali = "সকাল ০৬:৪০ থেকে সকাল ০৯:১৫ পর্যন্ত",
            auspiciousHoursEnglish = "6:40 AM to 9:15 AM",
            descriptionBengali = "বৈশাখী বাস্তু শান্তির শুভ দিন।",
            descriptionEnglish = "Auspicious Boishakh Vastu blessing."
        ),
        AuspiciousDate(
            id = "gp_2026_11_15",
            date = LocalDate.of(2026, 11, 15),
            category = AuspiciousCategory.GRIHA_PRAVESH,
            bengaliDateStr = "৬ অগ্রহায়ণ ১৪৩৩",
            bengaliDateStrEn = "6 Agrahayan 1433",
            tithi = "শুক্লা ষষ্ঠী",
            nakshatra = "শ্রবণা",
            auspiciousHoursBengali = "সকাল ০৯:১০ থেকে দুপুর ১২:০০ পর্যন্ত",
            auspiciousHoursEnglish = "9:10 AM to 12:00 PM",
            descriptionBengali = "গৃহশান্তি ও সৌভাগ্যের জন্য অতি ফলদায়ক।",
            descriptionEnglish = "Brings prosperity and peaceful domestic life."
        ),

        // Annaprashan (অন্নপ্রাশন)
        AuspiciousDate(
            id = "ap_2026_02_11",
            date = LocalDate.of(2026, 2, 11),
            category = AuspiciousCategory.ANNAPRASHAN,
            bengaliDateStr = "২৮ মাঘ ১৪৩২",
            bengaliDateStrEn = "28 Magh 1432",
            tithi = "কৃষ্ণা দশমী",
            nakshatra = "অনুরাধা",
            auspiciousHoursBengali = "সকাল ০৯:০০ থেকে দুপুর ১২:১৫ পর্যন্ত",
            auspiciousHoursEnglish = "9:00 AM to 12:15 PM",
            descriptionBengali = "শিশুর প্রথম অন্নগ্রহণ ও মুখে ভাতের শুভ লগ্ন।",
            descriptionEnglish = "Blessed first rice feeding ceremony for infant health."
        ),
        AuspiciousDate(
            id = "ap_2026_04_28",
            date = LocalDate.of(2026, 4, 28),
            category = AuspiciousCategory.ANNAPRASHAN,
            bengaliDateStr = "১৫ বৈশাখ ১৪৩৩",
            bengaliDateStrEn = "15 Boishakh 1433",
            tithi = "শুক্লা দ্বাদশী",
            nakshatra = "হস্ত",
            auspiciousHoursBengali = "সকাল ০৮:২০ থেকে সকাল ১১:৩০ পর্যন্ত",
            auspiciousHoursEnglish = "8:20 AM to 11:30 AM",
            descriptionBengali = "হস্তা নক্ষত্র যুক্ত অত্যন্ত পুণ্যময় অন্নপ্রাশন ক্ষণ।",
            descriptionEnglish = "Hasta nakshatra brings long life and intellect."
        ),
        AuspiciousDate(
            id = "ap_2026_10_22",
            date = LocalDate.of(2026, 10, 22),
            category = AuspiciousCategory.ANNAPRASHAN,
            bengaliDateStr = "১২ কার্তিক ১৪৩৩",
            bengaliDateStrEn = "12 Kartik 1433",
            tithi = "শুক্লা দ্বাদশী",
            nakshatra = "উত্তরভাদ্রপদ",
            auspiciousHoursBengali = "সকাল ০৯:১৫ থেকে দুপুর ০১:০০ পর্যন্ত",
            auspiciousHoursEnglish = "9:15 AM to 1:00 PM",
            descriptionBengali = "শিশুর মেধা ও সুস্বাস্থ্যের জন্য কল্যাণকর।",
            descriptionEnglish = "Promotes child's health and vitality."
        ),

        // Vehicle Purchase (গাড়ি ক্রয়)
        AuspiciousDate(
            id = "vp_2026_01_23",
            date = LocalDate.of(2026, 1, 23),
            category = AuspiciousCategory.VEHICLE_PURCHASE,
            bengaliDateStr = "৯ মাঘ ১৪৩২",
            bengaliDateStrEn = "9 Magh 1432",
            tithi = "শুক্লা ষষ্ঠী",
            nakshatra = "রেবতী",
            auspiciousHoursBengali = "দুপুর ০১:২০ থেকে বিকাল ০৪:১০ পর্যন্ত",
            auspiciousHoursEnglish = "1:20 PM to 4:10 PM",
            descriptionBengali = "নতুন যানবাহন ক্রয়ের জন্য অত্যন্ত শুভ সময়। ভ্রমণ হবে নিরাপদ।",
            descriptionEnglish = "Favorable planetary alignment for vehicle purchase."
        ),
        AuspiciousDate(
            id = "vp_2026_03_20",
            date = LocalDate.of(2026, 3, 20),
            category = AuspiciousCategory.VEHICLE_PURCHASE,
            bengaliDateStr = "৬ চৈত্র ১৪৩২",
            bengaliDateStrEn = "6 Chaitra 1432",
            tithi = "শুক্লা প্রতিপদ",
            nakshatra = "অশ্বিনী",
            auspiciousHoursBengali = "সকাল ১০:০০ থেকে দুপুর ০২:৩০ পর্যন্ত",
            auspiciousHoursEnglish = "10:00 AM to 2:30 PM",
            descriptionBengali = "শুভ অশ্বিনী নক্ষত্র গতিশীলতা ও নিরাপত্তার প্রতীক।",
            descriptionEnglish = "Ashwini constellation symbolizes smooth swift journeys."
        ),
        AuspiciousDate(
            id = "vp_2026_10_18",
            date = LocalDate.of(2026, 10, 18),
            category = AuspiciousCategory.VEHICLE_PURCHASE,
            bengaliDateStr = "৮ কার্তিক ১৪৩৩",
            bengaliDateStrEn = "8 Kartik 1433",
            tithi = "শুক্লা অষ্টমী",
            nakshatra = "শ্রবণা",
            auspiciousHoursBengali = "সকাল ০৯:৩০ থেকে দুপুর ০১:১৫ পর্যন্ত",
            auspiciousHoursEnglish = "9:30 AM to 1:15 PM",
            descriptionBengali = "গাড়ির সুরক্ষার জন্য শুভ লগ্ন।",
            descriptionEnglish = "Auspicious hour for vehicle delivery."
        ),

        // Business Opening & Hal Khata (ব্যবসা আরম্ভ)
        AuspiciousDate(
            id = "bo_2026_04_14",
            date = LocalDate.of(2026, 4, 14),
            category = AuspiciousCategory.BUSINESS_START,
            bengaliDateStr = "১ বৈশাখ ১৪৩৩",
            bengaliDateStrEn = "1 Boishakh 1433",
            tithi = "শুক্লা ত্রয়োদশী",
            nakshatra = "উত্তরাষাঢ়া",
            auspiciousHoursBengali = "সকাল ০৮:৩০ থেকে দুপুর ১২:০০, বিকাল ০৪:০০ থেকে সন্ধ্যা ০৭:৩০",
            auspiciousHoursEnglish = "8:30 AM - 12:00 PM, 4:00 PM - 7:30 PM",
            descriptionBengali = "পহেলা বৈশাখ - শুভ হালখাতা ও নতুন ব্যবসা আরম্ভের মহাসময়।",
            descriptionEnglish = "Bengali New Year: prime auspicious start for new ventures & ledger opening."
        ),
        AuspiciousDate(
            id = "bo_2026_04_24",
            date = LocalDate.of(2026, 4, 24),
            category = AuspiciousCategory.BUSINESS_START,
            bengaliDateStr = "১১ বৈশাখ ১৪৩৩",
            bengaliDateStrEn = "11 Boishakh 1433",
            tithi = "অক্ষয় তৃতীয়া",
            nakshatra = "রোহিণী",
            auspiciousHoursBengali = "সারাদিন অক্ষয় ও স্বতঃসিদ্ধ শুভ মুহূর্ত",
            auspiciousHoursEnglish = "Auspicious throughout the day (Akshaya Tritiya)",
            descriptionBengali = "অক্ষয় তৃতীয়া - যেকোনো ব্যবসা ও সোনা ক্রয়ের অক্ষয় ফলদায়ী দিন।",
            descriptionEnglish = "Akshaya Tritiya ensures perpetual success and prosperity."
        ),
        AuspiciousDate(
            id = "bo_2026_10_20",
            date = LocalDate.of(2026, 10, 20),
            category = AuspiciousCategory.BUSINESS_START,
            bengaliDateStr = "১০ কার্তিক ১৪৩৩",
            bengaliDateStrEn = "10 Kartik 1433",
            tithi = "বিজয়াদশমী",
            nakshatra = "শ্রবণা",
            auspiciousHoursBengali = "সকাল ০৯:৪৫ থেকে দুপুর ০২:১৫ পর্যন্ত",
            auspiciousHoursEnglish = "9:45 AM to 2:15 PM",
            descriptionBengali = "বিজয়ার আশীষে নতুন কাজে সর্বত্র সাফল্য লাভ।",
            descriptionEnglish = "Vijaya Dashami brings victory and enduring trade growth."
        )
    )

    val FESTIVALS: List<Festival> = listOf(
        Festival(
            id = "fest_poila_boishakh",
            nameBengali = "পহেলা বৈশাখ (শুভ নববর্ষ ১৪৩৩)",
            nameEnglish = "Poila Boishakh (Bengali New Year)",
            date = LocalDate.of(2026, 4, 14),
            bengaliDateStr = "১ বৈশাখ ১৪৩৩",
            type = FestivalType.CULTURAL,
            descriptionBengali = "বাঙালির প্রাণের নববর্ষ। মঙ্গল শোভাযাত্রা, হালখাতা ও মিষ্টিমুখের উৎসব।",
            descriptionEnglish = "Bengali New Year celebration with traditional processions and sweet exchanges.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_durga_puja_saptami",
            nameBengali = "মহা সপ্তমী (দুর্গাপূজা)",
            nameEnglish = "Maha Saptami (Durga Puja)",
            date = LocalDate.of(2026, 10, 17),
            bengaliDateStr = "৭ কার্তিক ১৪৩৩",
            type = FestivalType.PUJA_BRATA,
            descriptionBengali = "নবপত্রিকা প্রবেশ ও প্রাণপ্রতিষ্ঠার মধ্য দিয়ে শারদীয়া দুর্গোৎসবের শুভ সূচনা।",
            descriptionEnglish = "Sacred start of Durga Puja with Nabapatrika immersion and bathing.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_durga_puja_ashtami",
            nameBengali = "মহা অষ্টমী ও সন্ধিপূজা",
            nameEnglish = "Maha Ashtami & Sandhi Puja",
            date = LocalDate.of(2026, 10, 18),
            bengaliDateStr = "৮ কার্তিক ১৪৩৩",
            type = FestivalType.PUJA_BRATA,
            descriptionBengali = "দেবী দুর্গার পুষ্পাঞ্জলি, কুমারী পূজা ও অষ্টমী-নবমীর সন্ধিক্ষণে সন্ধিপূজা।",
            descriptionEnglish = "Peak day of Durga Puja with Kumari Puja and auspicious Sandhi Puja.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_durga_puja_navami",
            nameBengali = "মহা নবমী",
            nameEnglish = "Maha Navami",
            date = LocalDate.of(2026, 10, 19),
            bengaliDateStr = "৯ কার্তিক ১৪৩৩",
            type = FestivalType.PUJA_BRATA,
            descriptionBengali = "মহিষাসুরমর্দিনীর হোম ও আরতি। আনন্দের চরম মুহূর্ত।",
            descriptionEnglish = "Havan, special prayers, and cultural celebrations of Durga Puja.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_durga_puja_dashami",
            nameBengali = "বিজয়া দশমী",
            nameEnglish = "Vijaya Dashami",
            date = LocalDate.of(2026, 10, 20),
            bengaliDateStr = "১০ কার্তিক ১৪৩৩",
            type = FestivalType.PUJA_BRATA,
            descriptionBengali = "দেবী দুর্গার বিসর্জন, সিঁদুর খেলা ও প্রবীণদের প্রণাম ও শুভেচ্ছা বিনিময়।",
            descriptionEnglish = "Immersion of Goddess Durga, Sindur Khela, and exchanging Bijoya greetings.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_kojagari_lakshmi",
            nameBengali = "কোজাগরী লক্ষ্মী পূজা",
            nameEnglish = "Kojagari Lakshmi Puja",
            date = LocalDate.of(2026, 9, 25),
            bengaliDateStr = "১৫ আশ্বিন ১৪৩৩",
            type = FestivalType.PUJA_BRATA,
            descriptionBengali = "বাঙালির প্রতিটি ঘরে ঘরে মা লক্ষ্মীর বন্দনা ও আলপনা অঙ্কন।",
            descriptionEnglish = "Worship of the Goddess of Wealth on the auspicious autumn full moon.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_kali_puja",
            nameBengali = "শ্যামা কালীপূজা ও দীপাবলি",
            nameEnglish = "Kali Puja & Diwali",
            date = LocalDate.of(2026, 10, 10),
            bengaliDateStr = "৩০ আশ্বিন ১৪৩৩",
            type = FestivalType.PUJA_BRATA,
            descriptionBengali = "অমাবস্যার রাতে মা কালীর পূজা এবং চারিদিকে আলোর প্রদীপ সাজানো।",
            descriptionEnglish = "Nocturnal adoration of Mother Kali alongside festival of lamps.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_bhai_phota",
            nameBengali = "ভাইফোঁটা (ভ্রাতৃদ্বিতীয়া)",
            nameEnglish = "Bhai Phota",
            date = LocalDate.of(2026, 10, 12),
            bengaliDateStr = "২ কার্তিক ১৪৩৩",
            type = FestivalType.CULTURAL,
            descriptionBengali = "যমদুয়ারে দিলেম কাঁটা, ভাইয়ের কপালে দিলেম ফোঁটা - ভ্রাতৃত্ব ও স্নেহের অমর উৎসব।",
            descriptionEnglish = "Sisters apply sandalwood tilak on brothers' foreheads wishing them longevity.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_saraswati_puja",
            nameBengali = "সরস্বতী পূজা (বসন্ত পঞ্চমী)",
            nameEnglish = "Saraswati Puja (Vasant Panchami)",
            date = LocalDate.of(2026, 1, 23),
            bengaliDateStr = "৯ মাঘ ১৪৩২",
            type = FestivalType.PUJA_BRATA,
            descriptionBengali = "বিদ্যার দেবী মা সরস্বতীর পূজা এবং বাঙালির বাসন্তী শাড়ি ও পাঞ্জাবির উৎসব।",
            descriptionEnglish = "Adoration of Goddess Saraswati by students, artists, and scholars.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_dol_jatra",
            nameBengali = "দোলযাত্রা ও হোলি",
            nameEnglish = "Dol Jatra & Holi",
            date = LocalDate.of(2026, 3, 3),
            bengaliDateStr = "১৯ ফাল্গুন ১৪৩২",
            type = FestivalType.CULTURAL,
            descriptionBengali = "রঙের উৎসব এবং শান্তিনিকেতনের ঐতিহ্যবাহী বসন্তোৎসব।",
            descriptionEnglish = "Festival of colors, music, and spring joy.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_rabindra_jayanti",
            nameBengali = "রবীন্দ্র জয়ন্তী (পঁচিশে বৈশাখ)",
            nameEnglish = "Rabindra Jayanti (25th Boishakh)",
            date = LocalDate.of(2026, 5, 8),
            bengaliDateStr = "২৫ বৈশাখ ১৪৩৩",
            type = FestivalType.CULTURAL,
            descriptionBengali = "বিশ্বকবি রবীন্দ্রনাথ ঠাকুরের জন্মজয়ন্তী উদযাপন।",
            descriptionEnglish = "Birth anniversary of Nobel laureate Rabindranath Tagore.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_shivratri",
            nameBengali = "মহা শিবরাত্রি",
            nameEnglish = "Maha Shivratri",
            date = LocalDate.of(2026, 2, 16),
            bengaliDateStr = "৪ ফাল্গুন ১৪৩২",
            type = FestivalType.PUJA_BRATA,
            descriptionBengali = "শিবপূজা ও উপবাস পালনের শ্রেষ্ঠ রজনী।",
            descriptionEnglish = "Great Night of Lord Shiva with night-long meditation and puja.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_rath_yatra",
            nameBengali = "রথযাত্রা",
            nameEnglish = "Rath Yatra",
            date = LocalDate.of(2026, 6, 16),
            bengaliDateStr = "৩ আষাঢ় ১৪৩৩",
            type = FestivalType.PUJA_BRATA,
            descriptionBengali = "জগন্নাথ দেবের রথযাত্রা ও মেলা।",
            descriptionEnglish = "Chariot festival of Lord Jagannath, Balabhadra, and Subhadra.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_janmashtami",
            nameBengali = "শ্রীকৃষ্ণ জন্মাষ্টমী",
            nameEnglish = "Sri Krishna Janmashtami",
            date = LocalDate.of(2026, 9, 4),
            bengaliDateStr = "২৪ ভাদ্র ১৪৩৩",
            type = FestivalType.PUJA_BRATA,
            descriptionBengali = "ভগবান শ্রীকৃষ্ণের আবির্ভাব তিথি।",
            descriptionEnglish = "Celebration of the birth of Lord Krishna.",
            isMajorFestival = true
        ),
        Festival(
            id = "fest_mahalaya",
            nameBengali = "শুভ মহালয়া",
            nameEnglish = "Shubho Mahalaya",
            date = LocalDate.of(2026, 9, 10),
            bengaliDateStr = "৩০ ভাদ্র ১৪৩৩",
            type = FestivalType.PUJA_BRATA,
            descriptionBengali = "দেবী দুর্গার আগমন বার্তা ও তর্পণ।",
            descriptionEnglish = "Invocation of Goddess Durga heralding the festival season.",
            isMajorFestival = true
        )
    )

    fun isPurnimaDate(date: LocalDate): Boolean {
        return MOON_PHASE_EVENTS.any { it.type == MoonPhaseType.PURNIMA && it.date == date }
    }

    fun isAmavasyaDate(date: LocalDate): Boolean {
        return MOON_PHASE_EVENTS.any { it.type == MoonPhaseType.AMAVASYA && it.date == date }
    }

    fun getMoonPhaseEventForDate(date: LocalDate): MoonPhaseEvent? {
        return MOON_PHASE_EVENTS.find { it.date == date }
    }

    fun getFestivalForDate(date: LocalDate): Festival? {
        return FESTIVALS.find { it.date == date }
    }

    fun getAuspiciousEventsForDate(date: LocalDate): List<String> {
        return AUSPICIOUS_DATES.filter { it.date == date }
            .map { "${it.category.iconEmoji} ${it.category.bengaliName}" }
    }

    fun getNextMoonEvent(currentDate: LocalDate): MoonPhaseEvent? {
        return MOON_PHASE_EVENTS
            .filter { !it.date.isBefore(currentDate) }
            .minByOrNull { it.date }
    }
}
