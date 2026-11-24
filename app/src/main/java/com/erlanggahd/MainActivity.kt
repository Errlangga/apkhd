package com.erlanggahd

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

private val BG=Color(0xFF080D18); private val PANEL=Color(0xFF121C2D); private val BLUE=Color(0xFF5CA9FF); private val MUTED=Color(0xFF9AAAC1)
class MainActivity:ComponentActivity(){ override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState);setContent{Screen()}} }
@OptIn(ExperimentalMaterial3Api::class)
@Composable fun Screen(){
 var uri by remember{mutableStateOf<Uri?>(null)}; var kind by remember{mutableStateOf("image")}; var res by remember{mutableStateOf("1080p")}; var status by remember{mutableStateOf("Pilih media untuk memulai.")}
 val photo=rememberLauncherForActivityResult(ActivityResultContracts.GetContent()){if(it!=null){uri=it;kind="image";status="Foto dipilih."}}
 val video=rememberLauncherForActivityResult(ActivityResultContracts.GetContent()){if(it!=null){uri=it;kind="video";status="Video dipilih."}}
 MaterialTheme(colorScheme=darkColorScheme(background=BG,surface=PANEL,primary=BLUE,onSurface=Color.White)){ Scaffold(containerColor=BG){p-> Column(Modifier.fillMaxSize().padding(p).verticalScroll(rememberScrollState()).padding(22.dp),verticalArrangement=Arrangement.spacedBy(18.dp)){
 Row(verticalAlignment=Alignment.CenterVertically){Icon(Icons.Default.AutoAwesome,null,tint=BLUE,modifier=Modifier.size(34.dp));Spacer(Modifier.width(10.dp));Column{Text("ErlanggaHD",fontSize=22.sp,fontWeight=FontWeight.Bold);Text("AI PHOTO & VIDEO ENHANCER",fontSize=10.sp,color=MUTED)}}
 Text("Make it clearer.",fontSize=30.sp,fontWeight=FontWeight.Bold);Text("Tingkatkan kejernihan foto dan video.",color=MUTED)
 Surface(color=PANEL,shape=RoundedCornerShape(20.dp)){Column(Modifier.fillMaxWidth().padding(16.dp),horizontalAlignment=Alignment.CenterHorizontally){if(uri!=null&&kind=="image")AsyncImage(uri,null,modifier=Modifier.fillMaxWidth().height(220.dp)) else {Icon(if(kind=="video")Icons.Default.VideoLibrary else Icons.Default.Image,null,tint=BLUE,modifier=Modifier.size(56.dp));Text(if(uri==null)"Belum ada media" else "Video dipilih",color=MUTED)};Spacer(Modifier.height(12.dp));Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){Button(onClick={photo.launch("image/*")},modifier=Modifier.weight(1f)){Icon(Icons.Default.Image,null);Text("Foto")};OutlinedButton(onClick={video.launch("video/*")},modifier=Modifier.weight(1f)){Icon(Icons.Default.VideoLibrary,null);Text("Video")}}}}
 Text("OUTPUT RESOLUTION",color=MUTED,fontWeight=FontWeight.Bold);Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){listOf("720p","1080p").forEach{FilterChip(selected=res==it,onClick={res=it},label={Text(it)},modifier=Modifier.weight(1f))}}
 Button(onClick={status="Demo UI: backend AI belum tersambung."},enabled=uri!=null,modifier=Modifier.fillMaxWidth().height(54.dp)){Icon(Icons.Default.AutoAwesome,null);Spacer(Modifier.width(8.dp));Text("Enhance with AI")}
 Surface(color=PANEL,shape=RoundedCornerShape(12.dp)){Column(Modifier.padding(14.dp)){Text("STATUS",color=MUTED,fontSize=11.sp);Text(status)}}
 Text("Versi awal: UI dan pemilih media. Pemrosesan AI belum terhubung.",color=MUTED,fontSize=12.sp)
 } } }
}